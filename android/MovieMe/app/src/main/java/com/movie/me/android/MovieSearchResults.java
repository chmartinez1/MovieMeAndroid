package com.movie.me.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movie.me.android.controller.MovieListAdapter;
import com.movie.me.android.domain.Movie;

import java.util.List;

public class MovieSearchResults extends AppCompatActivity{
    private RecyclerView movieRecyclerView;
    private MovieListAdapter movieListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movieList;
    private String movieJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_search_results);

        movieJsonString = getIntent().getExtras().getString("result");

        movieList = new Gson().fromJson(movieJsonString, new TypeToken<List<Movie>>(){}.getType());

        Log.d("MovieList", movieList.toString());
        movieRecyclerView = (RecyclerView) findViewById(R.id.movie_result);

//        layoutManager = new LinearLayoutManager(this);

        layoutManager = new GridLayoutManager(this, 2);

        movieListAdapter = new MovieListAdapter(this, movieList);

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setAdapter(movieListAdapter);
        movieRecyclerView.setLayoutManager(layoutManager);

    }

}
