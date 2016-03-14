package com.chan.danny.bands;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danny on 3/13/16.
 */
public class Persistance {
    private interface DBTable
    {
        public String getTableName();
        public List<Pair<String, String>> getTableColumns();

    }

    private static class ExercisesTable implements DBTable
    {
        private final String mTableName = "exercises";
        private List<Pair<String, String>> mTableColumns;

        public ExercisesTable()
        {
            mTableColumns = new ArrayList<>();
            mTableColumns.add(new Pair<String, String>("color", "int"));
            mTableColumns.add(new Pair<String, String>("sets", "int"));
            mTableColumns.add(new Pair<String, String>("name", "text"));
        }

        public String getTableName()
        {
            return mTableName;
        }

        public List<Pair<String, String>> getTableColumns()
        {
            return mTableColumns;
        }
    }

//    private static class SqlLiteHelper extends SQLiteOpenHelper
//    {
//
//        private final static String sTableCreation = "CREATE TABLE " + sTableName + " (int color"
//
//        @Override
//        public void onCreate(SQLiteDatabase db) {
//            db.execSQL(DICTIONARY_TABLE_CREATE);
//        }
//
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//        }
//    }

    public static void save(List<Exercise> exercises)
    {

    }

    public static List<Exercise> load()
    {

    }
}
