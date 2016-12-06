package com.movie.me.android.rest;

import android.app.Activity;
import android.util.Log;

import com.movie.me.android.search.SearchResultProvider;
import com.movie.me.android.search.SearchSubscriber;

public class FetchSearchResultTask extends APICallTask {
    private final String TAG = FetchSearchResultTask.class.getSimpleName();
    private SearchResultProvider searchResultProvider;
    private SearchSubscriber searchSubscriber;
    private Activity activity;
    private String type;

    public FetchSearchResultTask(Activity activity, SearchResultProvider searchResultProvider,
                                 SearchSubscriber searchSubscriber) {
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

    public void setSearchType(String type) {
        this.path = type + "search?";
    }
}
