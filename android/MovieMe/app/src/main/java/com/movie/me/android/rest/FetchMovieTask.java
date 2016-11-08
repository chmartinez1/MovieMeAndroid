package com.movie.me.android.rest;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.movie.me.android.MovieSearchResults;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hargueta on 10/27/16.
 */
public class FetchMovieTask extends AsyncTask<String, Void, String> {
    private final String LOG_TAG = FetchMovieTask.class.getSimpleName();
    private Context context;

    public FetchMovieTask(Context context) {
        this.context = context;
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
            final String MOVIE_BASE_URL =
                    "http://ec2-35-162-141-107.us-west-2.compute.amazonaws.com:8080/movie/search?";

//            final String MOVIE_BASE_URL = "http:/198.189.249.55:8080/movie/search?";
            final String QUERY_PARAM = "title";

            Uri builtUri = Uri.parse(MOVIE_BASE_URL).buildUpon()
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
            Intent intent = new Intent(context, MovieSearchResults.class);
            String MOVIE_TEXT = result;
            intent.putExtra("result", result);
            context.startActivity(intent);
        }
    }
}
