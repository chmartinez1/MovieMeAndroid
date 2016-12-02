package com.movie.me.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movie.me.android.controller.MovieListAdapter;
import com.movie.me.android.domain.Movie;
import com.movie.me.android.rest.FetchMovieRecommendationsTask;
import com.movie.me.android.search.SearchActivity;
import com.movie.me.android.search.SearchSubscriber;
import com.movie.me.android.util.RecyclerViewClickSubscriber;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements RecyclerViewClickSubscriber, SearchSubscriber{

    private TextView moviesRecommendedLabel;
    private TextView defaultMoviesRecommendedTextView;

    private RecyclerView movieRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Movie> movieList;
    private String movieJsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        moviesRecommendedLabel= (TextView) findViewById(R.id.movies_recommended_label);
        defaultMoviesRecommendedTextView = (TextView) findViewById(R.id.default_movies_recommended_label);

        movieRecyclerView = (RecyclerView) findViewById(R.id.movie_result);
        Log.d("RECYCLERVIEW", "INSTANTIATED");
        if(movieList.size() != 0) {
            defaultMoviesRecommendedTextView.setVisibility(View.GONE);
        }

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setAdapter(new MovieListAdapter(this, new ArrayList<Movie>(),this));
        movieRecyclerView.setLayoutManager(layoutManager);
        new FetchMovieRecommendationsTask(this);

    }

    public void searchClick(View view){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }


    public void notifyClick(Object itemClicked){
        if(itemClicked instanceof View) {
            View viewClicked = (View) itemClicked;
            Movie movieClicked = movieList.get(movieRecyclerView.getChildAdapterPosition(viewClicked));
            Log.d("MOVIE CLICKED", movieClicked.getTitle());
            Intent intent = new Intent(this, MovieInfo.class);
            intent.putExtra("MOVIE_IMDBID",movieClicked.getImdbid());
            intent.putExtra("MOVIE_TITLE",movieClicked.getTitle());
            intent.putExtra("MOVIE_POSTER",movieClicked.getPoster());
            intent.putExtra("MOVIE_ACTORS",movieClicked.getActors());
            intent.putExtra("MOVIE_DATE",movieClicked.getReleaseDate());
            intent.putExtra("MOVIE_RATING",movieClicked.getRating());
            intent.putExtra("MOVIE_RATED",movieClicked.getRated());
            intent.putExtra("MOVIE_PLOT",movieClicked.getPlot());
            intent.putExtra("MOVIE_DIRECTOR",movieClicked.getDirector());
            intent.putExtra("MOVIE_RUNTIME",movieClicked.getRuntime());
            intent.putExtra("MOVIE_WRITER",movieClicked.getWriter());
            intent.putExtra("MOVIE_GENRE",movieClicked.getGenre());
            startActivity(intent);
        }
    }

    @Override
    public void notifyResult(String result) {
        Log.d("NOTIFY", "HomeActivity  was notified.");
        movieJsonString = result;
        Log.d("RESULT",result);
        movieList = new Gson().fromJson(movieJsonString, new TypeToken<List<Movie>>(){}.getType());
        Log.d("LIST",movieList.toString());
        MovieListAdapter movieListAdapter = new MovieListAdapter(this, movieList,this);
        movieRecyclerView.swapAdapter(movieListAdapter, false);
    }
}
