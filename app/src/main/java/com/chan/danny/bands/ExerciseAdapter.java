package com.chan.danny.bands;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by danny on 3/13/16.
 */
public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView mNameView;
        private LinearLayout mColorsView;
        private TextView mSetsView;
        private Context mContext;

        public ViewHolder(Context c, View v)
        {
            super(v);
            mContext = c;
            mNameView = (TextView)v.findViewById(R.id.exercise_name);
            mColorsView = (LinearLayout)v.findViewById(R.id.exercise_colors);
            mSetsView = (TextView)v.findViewById(R.id.exercise_sets);
        }

        private void configure(Exercise exercise)
        {
            mNameView.setText(exercise.getName());
            mSetsView.setText(exercise.getNumSets() + "");
            mColorsView.removeAllViews();

            for (int color : exercise.getColors())
            {
                View v = new View(mContext);
                v.setBackgroundColor(color);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(44, 44);
                lp.setMargins(0, 10 , 10, 10);
                mColorsView.addView(v, lp);
            }
        }
    }

    private List<Exercise> mExercises;

    public ExerciseAdapter(List<Exercise> exercises)
    {
        mExercises = exercises;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(parent.getContext(), v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Exercise exercise = mExercises.get(position);
        holder.configure(exercise);
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }
}
