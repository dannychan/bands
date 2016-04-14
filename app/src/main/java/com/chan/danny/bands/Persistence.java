package com.chan.danny.bands;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatatypeMismatchException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 3/13/16.
 */
public class Persistence {
    private interface ExerciseTable {
        String TABLE_NAME = "Exercises";
        String COL_NAME = "name";
        String COL_SETS = "sets";
        String COL_ORDER = "eorder";
    }

    private interface ColorTable {
        String TABLE_NAME = "Color";
        String COL_COLOR = "color";
        String COL_EXERCISE = "exercise";
    }

    private SQLiteDatabase mDb;

    public Persistence(Context context) {
        SQLiteOpenHelper helper = new SqlLiteHelper(context);
        mDb = helper.getWritableDatabase();
    }

    private static class SqlLiteHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "Bands.db";

        public SqlLiteHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        private final static String sExerciseTableCreate = "CREATE TABLE IF NOT EXISTS " + ExerciseTable.TABLE_NAME + " (" +
                ExerciseTable.COL_NAME + " varchar(100) PRIMARY KEY, " +
                ExerciseTable.COL_SETS + " int, " +
                ExerciseTable.COL_ORDER + " int)";

        private final static String sColorTableCreate = "CREATE TABLE IF NOT EXISTS " + ColorTable.TABLE_NAME + " (" +
                ColorTable.COL_EXERCISE + " REFERENCES " + ExerciseTable.TABLE_NAME + "(" + ExerciseTable.COL_NAME + "), " +
                ColorTable.COL_COLOR + " int)";

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sExerciseTableCreate);
            db.execSQL(sColorTableCreate);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public void save(List<Exercise> exercises) {
        mDb.delete(ExerciseTable.TABLE_NAME, null, null);
        mDb.delete(ColorTable.TABLE_NAME, null, null);

        mDb.beginTransaction();
        try {
            for (Exercise exercise : exercises) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(ExerciseTable.COL_NAME, exercise.getName());
                contentValues.put(ExerciseTable.COL_SETS, exercise.getNumSets());
                contentValues.put(ExerciseTable.COL_ORDER, exercise.getOrder());
                mDb.insert(ExerciseTable.TABLE_NAME, null, contentValues);

                for (int color : exercise.getColors()) {
                    ContentValues colorValues = new ContentValues();
                    colorValues.put(ColorTable.COL_EXERCISE, exercise.getName());
                    colorValues.put(ColorTable.COL_COLOR, color);
                    mDb.insert(ColorTable.TABLE_NAME, null, colorValues);
                }
            }

            mDb.setTransactionSuccessful();
        } finally {
            mDb.endTransaction();
        }
    }

    public List<Exercise> load() {
        List<Exercise> exercises = new ArrayList<>();

        Cursor cursor = mDb.query(ExerciseTable.TABLE_NAME, null, null, null, null, null, ExerciseTable.COL_ORDER);
        while(cursor.moveToNext()) {
            Exercise.Builder builder = new Exercise.Builder();
            String name = cursor.getString(cursor.getColumnIndex(ExerciseTable.COL_NAME));
            builder.setName(name);
            builder.setSets(cursor.getInt(cursor.getColumnIndex(ExerciseTable.COL_SETS)));
            builder.setOrder(cursor.getInt(cursor.getColumnIndex(ExerciseTable.COL_ORDER)));

            Cursor cursor1 = mDb.query(ColorTable.TABLE_NAME, null, ColorTable.COL_EXERCISE + " = ?", new String[]{name}, null, null, null);
            while(cursor1.moveToNext()) {
                builder.addColor(cursor1.getInt(cursor1.getColumnIndex(ColorTable.COL_COLOR)));
            }

            exercises.add(builder.build());
        }

        return exercises;
    }
}
