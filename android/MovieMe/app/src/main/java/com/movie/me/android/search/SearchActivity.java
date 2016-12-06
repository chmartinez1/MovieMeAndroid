package com.movie.me.android.search;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.movie.me.android.R;
import com.movie.me.android.rest.FetchSearchResultTask;
import com.movie.me.android.util.SignedInUserPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchResultProvider {
    int position = 0;
    SearchResultProvider resultProvider;

    ViewPager viewPager;
    SearchFragmentPageAdapter adapter;

    List<SearchSubscriber> searchSubscribers;
    FetchSearchResultTask searchResultTask;

    SearchActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        SharedPreferences sharedPreferences = this.getSharedPreferences(SignedInUserPreferences.SHARED_PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SignedInUserPreferences.USER_ID_KEY, "12345");
        editor.putString(SignedInUserPreferences.USERNAME_KEY, "juice94");
        editor.putString(SignedInUserPreferences.EMAIL_KEY, "hugoargueta94@gmail.com");
        editor.apply();

        resultProvider = this;

        searchSubscribers = new ArrayList<>();

        activity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Movie"));
        tabLayout.addTab(tabLayout.newTab().setText("User"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager) findViewById(R.id.pager);
        adapter = new SearchFragmentPageAdapter
                (getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                viewPager.setCurrentItem(position);

                searchResultTask = null;
                setSearchResultTask();
                Log.d("TAB", "onTabSelected");

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("TAB", "onTabUnselected");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("TAB", "onTabReselected");
            }
        });

        Log.d("POSITION", position + "");
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchItem.expandActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                HashMap<String, String> map = new HashMap<>();

                switch(position) {
                    case 0:
                        map.put("property", "title");
                        map.put("value", query);
                        searchResultTask.setup(map).execute("GET");
                        break;
                    case 1:
                        map.put("name", query);
                        searchResultTask.setup(map).execute("GET");
                        break;
                }
                searchView.clearFocus();

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void subscribe(SearchSubscriber subscriber) {
        Log.d("SUBSCRIBED", "SUBSCRIBER WAS ATTACHED");

        searchSubscribers.add(subscriber);
        setSearchResultTask();
    }

    @Override
    public void resetFetchSearchResultTask() {
        setSearchResultTask();
    }

    public void setSearchResultTask() {
        switch(position) {
            case 0:
                searchResultTask = new FetchSearchResultTask(this,this,searchSubscribers.get(0));
                searchResultTask.setSearchType("movie/");
                break;
            case 1:
                searchResultTask = new FetchSearchResultTask(this,this,searchSubscribers.get(1));
                searchResultTask.setSearchType("user/");
                break;
        }
    }
}
