package com.movie.me.android;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movie.me.android.controller.MovieListAdapter;
import com.movie.me.android.domain.Movie;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSearchResultFragment extends Fragment {

    public MovieSearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Bundle args = getArguments();

//        movieJsonString = args.getString("result");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_search_result, container, false);

        return view;
    }

    public static MovieSearchResultFragment newInstance(String searchResult) {
        Bundle args = new Bundle();
        args.putString("result", searchResult);

        MovieSearchResultFragment fragment = new MovieSearchResultFragment();
        fragment.setArguments(args);

        return fragment;
    }

}
