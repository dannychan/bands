package com.chan.danny.bands.fragment;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chan.danny.bands.Exercise;
import com.chan.danny.bands.ExerciseAdapter;
import com.chan.danny.bands.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by danny on 3/13/16.
 */
public class ExercisesFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exercies, container);

        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<Exercise> exercises = new ArrayList<>();
        Exercise exercise = new Exercise.Builder()
                .addColor(Color.RED)
                .addColor(Color.YELLOW)
                .setName("Ab Twist")
                .setSets(10)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.GREEN)
                .addColor(Color.parseColor("purple"))
                .addColor(Color.BLACK)
                .setName("Squat")
                .setSets(20)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .setName("Plank")
                .setSets(130)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .setName("Side plank")
                .setSets(100)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.RED)
                .setName("Pull out sword")
                .setSets(15)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.BLUE)
                .addColor(Color.parseColor("purple"))
                .setName("Pull")
                .setSets(20)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.parseColor("purple"))
                .setName("push")
                .setSets(20)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.parseColor("purple"))
                .addColor(Color.BLACK)
                .setName("bicep")
                .setSets(15)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.RED)
                .addColor(Color.BLUE)
                .setName("pull down")
                .setSets(20)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.parseColor("purple"))
                .setName("in")
                .setSets(20)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.parseColor("purple"))
                .setName("up")
                .setSets(20)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .addColor(Color.rgb(255, 165, 0))
                .setName("side")
                .setSets(15)
                .build();
        exercises.add(exercise);

        exercise = new Exercise.Builder()
                .setName("Push up")
                .setSets(15)
                .build();
        exercises.add(exercise);

        Collections.shuffle(exercises);

        recyclerView.setAdapter(new ExerciseAdapter(exercises));
        mRecyclerView = recyclerView;

        return v;
    }
}
