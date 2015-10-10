package com.example.manuelsanchez.popularmovies;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivityFragment extends Fragment {

    private MoviesAdapter moviesAdapter;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        moviesAdapter = new MoviesAdapter(getActivity(), R.layout.movie_item);
        new MoviesAsyncTask(moviesAdapter).execute();

        GridView gridView = (GridView) view.findViewById(R.id.movieGrid);
        gridView.setAdapter(moviesAdapter);
        return view;
    }
}
