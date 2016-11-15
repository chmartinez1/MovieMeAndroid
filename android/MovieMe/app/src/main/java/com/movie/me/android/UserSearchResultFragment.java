package com.movie.me.android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserSearchResultFragment extends Fragment {


    public UserSearchResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_search_result, container, false);
    }

    public static UserSearchResultFragment newInstance(String searchResult) {
        Bundle args = new Bundle();
        args.putString("result", searchResult);

        UserSearchResultFragment fragment = new UserSearchResultFragment();
        fragment.setArguments(args);

        return fragment;
    }
}
