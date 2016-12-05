package com.movie.me.android.rest;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

public abstract class APICallTask extends AsyncTask<String, Void, String> {
    private final String TAG = APICallTask.class.getSimpleName();
    private final String DOMAIN = "http://35.160.154.211:8080/";
    private HttpURLConnection connection;
    protected String path;

    public APICallTask setup(Map<String, String> params) {
        try {
            Iterator it = params.entrySet().iterator();

            Uri.Builder builder = new Uri.Builder();
            builder = Uri.parse(DOMAIN + path).buildUpon();

            while(it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                builder.appendQueryParameter(pair.getKey().toString(), pair.getValue().toString());
            }

            URL url = new URL(builder.build().toString());

            connection = (HttpURLConnection) url.openConnection();

            Log.d(TAG, "setup complete");
        } catch(Exception e) {
            Log.e(TAG, "Exception: " + e.toString());
            e.printStackTrace();
        }

        return this;
    }

    @Override
    protected String doInBackground(String... params) {
        String json = null;
        BufferedReader reader = null;

        try {
            connection.setRequestMethod(params[0]);
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

            json = buffer.toString();
        } catch(Exception e) {
            Log.e(TAG, "Exception: " + e.toString());
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }

            if (reader != null) {
                try {
                    reader.close();
                } catch (final Exception e) {
                    Log.e(TAG, "Exception: " + e);
                    e.printStackTrace();
                }
            }


            Log.d(TAG, "doInBackground complete");
            return json;
        }
    }
}
