package com.movie.me.android.rest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movie.me.android.domain.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by psycho on 11/30/16.
 */

public class ServerSideSigninTask extends AsyncTask<String, Void, String> {
    private Activity activity;
    private String idToken;

    public ServerSideSigninTask(Activity activity, String idToken) {
        this.activity = activity;
        this.idToken = idToken;
    }

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String userJsonStr = null;

        try {
            String BASE_URL = "";
            ArrayList<String> QUERY_PARAMS = new ArrayList<String>(Arrays.asList("idToken"));

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAMS.get(0), params[0])
                    .build();

            URL url = new URL(builtUri.toString());

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            userJsonStr = buffer.toString();

        } catch(Exception e) {
            // TODO: handle exception
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }

        return userJsonStr;
    }

    @Override
    public void onPostExecute(String result) {
        if( result != null ) {
            Intent intent = new Intent(this.activity, HomeActivity.class);
            intent.putExtra("userJsonStr", result);
            this.activity.startActivity(intent);
        }
    }
}
