package com.movie.me.android.search;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.movie.me.android.R;
import com.movie.me.android.controller.UserListAdapter;
import com.movie.me.android.domain.User;
import com.movie.me.android.util.RecyclerViewClickSubscriber;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserSearchResultFragment extends Fragment implements SearchSubscriber, RecyclerViewClickSubscriber {
    private RecyclerView userRecyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<User> userList;
    private String userJsonString;
    Context context;

    SearchResultProvider searchResultProvider;

    public UserSearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.d("ATTACH", "Fragment User was attached.");

        searchResultProvider = (SearchResultProvider) activity;
        searchResultProvider.subscribe(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_search_result, container, false);

        Log.d("VIEW", "USER VIEW ATTACHED");

        userRecyclerView = (RecyclerView) view.findViewById(R.id.user_result);

        layoutManager = new LinearLayoutManager(context);

        userRecyclerView.setHasFixedSize(true);
        userRecyclerView.setAdapter(new UserListAdapter(context, new ArrayList<User>(), this));
        userRecyclerView.setLayoutManager(layoutManager);

        return view;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void notifyResult(String result) {
        Log.d("NOTIFY", "User Fragment was notified.");
        userJsonString = result;
        userList = new Gson().fromJson(userJsonString, new TypeToken<List<User>>(){}.getType());

        UserListAdapter userListAdapter = new UserListAdapter(context, userList, this);
        userRecyclerView.swapAdapter(userListAdapter, false);
    }

    public static UserSearchResultFragment newInstance(Context context) {

        UserSearchResultFragment fragment = new UserSearchResultFragment();
        fragment.setContext(context);

        return fragment;
    }

    @Override
    public void notifyClick(Object itemClicked) {
        if(itemClicked instanceof View) {
            View viewClicked = (View) itemClicked;
            User userClicked = userList.get(userRecyclerView.getChildAdapterPosition(viewClicked));
            Log.d("USER CLICKED", userClicked.getName());
        }
    }
}
