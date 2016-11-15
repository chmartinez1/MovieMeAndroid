package com.movie.me.android;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by hargueta on 11/8/16.
 */

public class SearchFragmentPageAdapter extends FragmentStatePagerAdapter {
    int numTabs;

    public SearchFragmentPageAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                MovieSearchResultFragment movieFragment = new MovieSearchResultFragment();
                return movieFragment;
            case 1:
                UserSearchResultFragment userFragment = new UserSearchResultFragment();
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
