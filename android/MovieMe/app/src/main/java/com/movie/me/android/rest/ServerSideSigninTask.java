package com.movie.me.android.rest;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServerSideSigninTask extends AsyncTask<String, Void, String> {
    private final String LOG_TAG = ServerSideSigninTask.class.getSimpleName();
    private String idtoken;

    public ServerSideSigninTask(String idtoken) {
        Log.d(LOG_TAG, "hello world!");
        this.idtoken = idtoken;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection;
        BufferedReader reader;
        String userJsonString = "";

        try {
            String baseurl = "10.0.0.2:8080/signin?";
            String queryparam = "idtoken";

            // set query params
            Uri builtUri = Uri.parse(baseurl).buildUpon()
                    .appendQueryParameter(queryparam, params[0])
                    .build();

            URL url = new URL(builtUri.toString());
            Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            // Create the request to MovieMe, and open the connection
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();

            // Read the input stream into a String
            InputStream inputStream = connection.getInputStream();
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

            userJsonString = buffer.toString();
            Log.v(LOG_TAG, "Json String " + userJsonString.toString());
        } catch(IOException e) {

        }

        return userJsonString;
    }
}
