package com.movie.me.android.search;


import android.app.Activity;
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
import com.movie.me.android.R;
import com.movie.me.android.controller.MovieListAdapter;
import com.movie.me.android.domain.Movie;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieSearchResultFragment extends Fragment implements SearchSubscriber {
    private RecyclerView movieRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movieList;
    private String movieJsonString;
    private Context context;

    SearchResultProvider searchResultProvider;

    public MovieSearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d("ATTACH", "Fragment Movie was attached");

        searchResultProvider = (SearchResultProvider) activity;
        searchResultProvider.subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_search_result, container, false);

        Log.d("VIEW", "MOVIE FRAGMENT ATTACHED");

        movieRecyclerView = (RecyclerView) view.findViewById(R.id.movie_result);

        Log.d("RECYCLERVIEW", "INSTANTIATED");

        layoutManager = new GridLayoutManager(context, 2);

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setAdapter(new MovieListAdapter(context, new ArrayList<Movie>()));
        movieRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void notifyResult(String result) {
        Log.d("NOTIFY", "Movie Fragment was notified.");
        movieJsonString = result;
        movieList = new Gson().fromJson(movieJsonString, new TypeToken<List<Movie>>(){}.getType());

        MovieListAdapter movieListAdapter = new MovieListAdapter(context, movieList);
        movieRecyclerView.swapAdapter(movieListAdapter, false);
    }

    public static MovieSearchResultFragment newInstance(Context context) {

        MovieSearchResultFragment fragment = new MovieSearchResultFragment();
        fragment.setContext(context);

        return fragment;
    }
}
