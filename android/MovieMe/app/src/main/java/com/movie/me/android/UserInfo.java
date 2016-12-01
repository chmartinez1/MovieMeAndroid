package com.movie.me.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movie.me.android.controller.MovieListAdapter;
import com.movie.me.android.domain.Movie;
import com.movie.me.android.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class UserInfo extends AppCompatActivity {
    private TextView usernameTextView;
    private ImageView profilePhotoImageView;
    private Button addFriendButton;
    private TextView moviesLikedLabel;
    private TextView defaultMoviesLikedTextView;

    private String username;
    private String photoURI;
    private String userId;
    private String moviesLikedJsonString;

    private RecyclerView movieRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent sentIntent = getIntent();
        username = sentIntent.getStringExtra("username");
        photoURI = sentIntent.getStringExtra("profileImage");
        userId = sentIntent.getStringExtra("userId");
        moviesLikedJsonString = sentIntent.getStringExtra("moviesLiked");

        movieList = new Gson().fromJson(moviesLikedJsonString, new TypeToken<List<Movie>>(){}.getType());

        Log.d("MovieList", movieList.toString());

        usernameTextView = (TextView) findViewById(R.id.username_textview);
        profilePhotoImageView = (ImageView) findViewById(R.id.user_info_image);
        addFriendButton = (Button) findViewById(R.id.add_friend);
        moviesLikedLabel = (TextView) findViewById(R.id.movies_liked_label);
        defaultMoviesLikedTextView = (TextView) findViewById(R.id.default_movies_liked_label);

        if(movieList.size() != 0) {
            defaultMoviesLikedTextView.setVisibility(View.GONE);
        }

        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        usernameTextView.setText(username);
        Picasso.with(this).load(photoURI).transform(new RoundedTransformation()).into(profilePhotoImageView);

        if(username != null)
            moviesLikedLabel.setText("Movies Liked By " + username + ":");

        movieRecyclerView = (RecyclerView) findViewById(R.id.movies_liked_by_user);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setAdapter(new MovieListAdapter(this, movieList));
        movieRecyclerView.setLayoutManager(layoutManager);

    }
}