package com.movie.me.android.rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.movie.me.android.UserInfo;
import com.movie.me.android.domain.User;
import com.movie.me.android.search.SearchResultProvider;
import com.movie.me.android.search.SearchSubscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hargueta on 11/30/16.
 */

public class FetchMoviesLikedByUserTask extends AsyncTask<String, Void, String> {
    private final String LOG_TAG = FetchSearchResultTask.class.getSimpleName();
    private Activity activity;
    private User user;

    private ProgressDialog dialog;

    public FetchMoviesLikedByUserTask(Activity activity, User user) {
        this.activity = activity;
        this.user = user;
        this.dialog = new ProgressDialog((this.activity));
    }

    protected void onPreExecute() {
//        this.dialog.setMessage("Adding friend...");
//        this.dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        // Will contain the raw JSON response as a string.
        String movieJsonStr = null;


        try {
            // Construct the URL for the MovieMe query
            String SEARCH_BASE_URL =
                    "http://ec2-35-165-1-224.us-west-2.compute.amazonaws.com:8080/user/view_likes?";

            String QUERY_PARAM = "userid";

            Uri builtUri = Uri.parse(SEARCH_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, params[0])
                    .build();

            URL url = new URL(builtUri.toString());
            Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            // Create the request to MovieMe, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a lot easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty. No point in parsing.
                Log.d("Empty", "Empty");
                return null;
            }

            movieJsonStr = buffer.toString();
            Log.v(LOG_TAG, "Json String " + movieJsonStr.toString());


        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);

            // If the code didn't successfully get the movie data, there's no point in attempting
            // to parse it.
            movieJsonStr = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        return movieJsonStr;
    }

    @Override
    public void onPostExecute(String result) {
        if (result != null) {
            Log.d("Result", result);

            Intent i = new Intent(this.activity, UserInfo.class);
            i.putExtra("profileImage", user.getPhotoURI());
            i.putExtra("userid", user.getUserid());
            i.putExtra("moviesLiked", result);
            this.activity.startActivity(i);
        } else {
            Log.d("Result", "Null result");
        }
    }
}
