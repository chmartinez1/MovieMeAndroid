package com.movie.me.android.controller;

import com.movie.me.android.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.movie.me.android.R;
import com.movie.me.android.domain.Movie;
import com.movie.me.android.util.RecyclerViewClickSubscriber;
import com.squareup.picasso.Picasso;
import java.io.InputStream;
import java.util.List;
import static android.support.v7.recyclerview.R.styleable.RecyclerView;


/**
 * Created by hargueta on 10/27/16.
 */


public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Movie> movieResultList;
    private LayoutInflater layoutInflater;
    private RecyclerViewClickSubscriber subscriber;


    public MovieListAdapter(Context context, List<Movie> movieResultList, RecyclerViewClickSubscriber subscriber) {
        this.context = context;
        this.movieResultList = movieResultList;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.subscriber = subscriber;
    }




    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView = layoutInflater.inflate(R.layout.single_movie_result, parent, false);
        movieView.setOnClickListener(this);
        MovieViewHolder movieHolder = new MovieViewHolder(movieView);
        return movieHolder;
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie currMovie = movieResultList.get(position);
        Picasso.with(context).load(currMovie.getPoster()).resize(100, 150).centerCrop().into(holder.moviePoster);
        holder.movieTitle.setText(currMovie.getTitle());
    }
    @Override
    public void onClick(View view) {
        subscriber.notifyClick(view);
    }


    @Override
    public int getItemCount() {
        return movieResultList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        ImageView moviePoster;
        TextView movieTitle;


        public MovieViewHolder(View itemView) {
            super(itemView);
            this.moviePoster = (ImageView) itemView.findViewById(R.id.poster_image);
            this.movieTitle = (TextView) itemView.findViewById(R.id.title);
        }
    }
}


