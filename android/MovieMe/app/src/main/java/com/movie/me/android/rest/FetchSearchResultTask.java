package com.movie.me.android.rest;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.movie.me.android.search.SearchResultProvider;
import com.movie.me.android.search.SearchSubscriber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchSearchResultTask extends APICallTask {
    private final String TAG = FetchSearchResultTask.class.getSimpleName();
    private SearchResultProvider searchResultProvider;
    private SearchSubscriber searchSubscriber;
    private Activity activity;

    public FetchSearchResultTask(Activity activity, SearchResultProvider searchResultProvider, SearchSubscriber searchSubscriber) {
        this.path = "movie/search?";
        this.searchResultProvider = searchResultProvider;
        this.searchSubscriber = searchSubscriber;
        this.activity = activity;
    }

    @Override
    public void onPostExecute(String result) {
        if (result != null) {
            searchSubscriber.notifyResult(result);
            searchResultProvider.resetFetchSearchResultTask();
            Log.d(TAG, result);
        } else {
            Log.d(TAG, "null");
        }
    }
}
