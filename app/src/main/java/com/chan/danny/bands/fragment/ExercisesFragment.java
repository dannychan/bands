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
import android.widget.Button;

import com.chan.danny.bands.Exercise;
import com.chan.danny.bands.ExerciseAdapter;
import com.chan.danny.bands.Persistence;
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

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final List<Exercise> exercises = load();

        Button button = (Button)v.findViewById(R.id.shuffle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Exercise> shuffledExercies = new ArrayList<Exercise>();
                Collections.shuffle(exercises);
                Persistence p = new Persistence(getActivity());
                for (int i = 0; i < exercises.size(); i++) {
                    Exercise e = exercises.get(i);
                    Exercise.Builder builder = new Exercise.Builder().setName(e.getName()).setOrder(i).setSets(e.getNumSets());
                    for (int color : e.getColors()) {
                        builder.addColor(color);
                    }

                    shuffledExercies.add(builder.build());
                }
                p.save(shuffledExercies);
                mRecyclerView.setAdapter(new ExerciseAdapter(shuffledExercies));
            }
        });

        mRecyclerView.setAdapter(new ExerciseAdapter(exercises));

        return v;
    }

    private List<Exercise> load() {
        Persistence p = new Persistence(getActivity());

        List<Exercise> exercises = p.load();
        if (exercises.size() < 1) {
            Exercise exercise = new Exercise.Builder()
                    .addColor(Color.RED)
                    .addColor(Color.YELLOW)
                    .setName("Ab Twist")
                    .setSets(10)
                    .setOrder(0)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.GREEN)
                    .addColor(Color.parseColor("purple"))
                    .addColor(Color.BLACK)
                    .setName("Squat")
                    .setSets(20)
                    .setOrder(1)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .setName("Plank")
                    .setSets(130)
                    .setOrder(2)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .setName("Side plank")
                    .setSets(100)
                    .setOrder(3)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.RED)
                    .setName("Pull out sword")
                    .setSets(15)
                    .setOrder(4)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.BLUE)
                    .addColor(Color.parseColor("purple"))
                    .setName("Pull")
                    .setSets(20)
                    .setOrder(5)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.parseColor("purple"))
                    .setName("push")
                    .setSets(20)
                    .setOrder(6)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.parseColor("purple"))
                    .addColor(Color.BLACK)
                    .setName("bicep")
                    .setSets(15)
                    .setOrder(7)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.RED)
                    .addColor(Color.BLUE)
                    .setName("pull down")
                    .setSets(20)
                    .setOrder(8)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.parseColor("purple"))
                    .setName("in")
                    .setSets(20)
                    .setOrder(9)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.parseColor("purple"))
                    .setName("up")
                    .setSets(20)
                    .setOrder(10)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .addColor(Color.rgb(255, 165, 0))
                    .setName("side")
                    .setSets(15)
                    .setOrder(11)
                    .build();
            exercises.add(exercise);

            exercise = new Exercise.Builder()
                    .setName("Push up")
                    .setSets(15)
                    .setOrder(12)
                    .build();
            exercises.add(exercise);

            p.save(exercises);
        }

        return exercises;
    }
}
