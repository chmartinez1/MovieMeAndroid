package com.movie.me.android.search;

/**
 * Created by hargueta on 11/18/16.
 */

public interface SearchResultProvider {
    void subscribe(SearchSubscriber subscriber);
    void resetFetchSearchResultTask();
}
