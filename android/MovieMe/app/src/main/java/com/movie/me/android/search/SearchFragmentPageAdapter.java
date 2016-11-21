package com.movie.me.android.search;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hargueta on 11/8/16.
 */

public class SearchFragmentPageAdapter extends FragmentStatePagerAdapter {
    int numTabs;
    Context context;

    public SearchFragmentPageAdapter(FragmentManager fm, Context context, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                MovieSearchResultFragment movieFragment = MovieSearchResultFragment.newInstance(this.context);
                return movieFragment;
            case 1:
                UserSearchResultFragment userFragment = UserSearchResultFragment.newInstance(this.context);
                return userFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.numTabs;
    }
}
