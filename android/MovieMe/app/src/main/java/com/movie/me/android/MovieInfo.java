package com.movie.me.android;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.me.android.rest.LikeMovieTask;
import com.movie.me.android.util.SignedInUserPreferences;
import com.squareup.picasso.Picasso;
import org.w3c.dom.Text;


public class MovieInfo extends AppCompatActivity implements View.OnClickListener {


    private String title;
    private String actors;
    private String rating;
    private String rated;
    private String director;
    private String runtime;
    private String genre;
    private String date;
    private String plot;
    private String writers;
    private String poster;
    private String imdbid;
    TextView titleTextView;
    TextView actorsTextView;
    TextView ratingTextView;
    TextView ratedTextView;
    TextView directorTextView;
    TextView runtimeTextView;
    TextView genreTextView;
    TextView dateTextView;
    TextView plotTextView;
    TextView writersTextView;
    ImageView posterImageView;
    Button likeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        Bundle extras = getIntent().getExtras();


        if(extras != null) {
            title = "Title: " + extras.getString("MOVIE_TITLE");
            actors = "Actors: " + extras.getString("MOVIE_ACTORS");
            rating = "Rating: " + extras.getString("MOVIE_RATING");
            rated = "Rated: " + extras.getString("MOVIE_RATED");
            director = "Director: " + extras.getString("MOVIE_DIRECTOR");
            runtime = "Runtime: " + extras.getString("MOVIE_RUNTIME");
            genre = "Genre: " + extras.getString("MOVIE_GENRE");
            date = "Date: " + extras.getString("MOVIE_DATE");
            plot = "Plot: " + extras.getString("MOVIE_PLOT");
            writers = "Writers: " + extras.getString("MOVIE_WRITERS");
            poster = extras.getString("MOVIE_POSTER");
            imdbid = extras.getString("MOVIE_IMDBID");
        }


        this.titleTextView = (TextView) findViewById(R.id.movie_title);
        this.titleTextView.setText(title);
        this.actorsTextView = (TextView) findViewById(R.id.movie_actors);
        this.actorsTextView.setText(actors);
        this.directorTextView = (TextView) findViewById(R.id.movie_director);
        this.directorTextView.setText(director);
        this.runtimeTextView = (TextView) findViewById(R.id.movie_runtime);
        this.runtimeTextView.setText(runtime);
        this.genreTextView = (TextView) findViewById(R.id.movie_genre);
        this.genreTextView.setText(genre);
        this.dateTextView = (TextView) findViewById(R.id.movie_release_date);
        this.dateTextView.setText(date);
        this.plotTextView = (TextView) findViewById(R.id.movie_summary);
        this.plotTextView.setText(plot);
        this.writersTextView = (TextView) findViewById(R.id.movie_writers);
        this.writersTextView.setText(writers);
        this.ratedTextView = (TextView) findViewById(R.id.movie_rated);
        this.ratedTextView.setText(rated);
        this.ratingTextView = (TextView) findViewById(R.id.movie_rating);
        this.ratingTextView.setText(rating);
        this.likeButton = (Button) findViewById(R.id.like_button);
        likeButton.setOnClickListener(this);


        this.posterImageView = (ImageView)findViewById(R.id.movie_poster_image);


        Picasso.with(this).load(poster).resize(75,107).into(posterImageView);




    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.like_button) {
            String currUserid = SignedInUserPreferences.getUserid(this);

            new LikeMovieTask(this).execute(currUserid, imdbid);
        }
    }
}


