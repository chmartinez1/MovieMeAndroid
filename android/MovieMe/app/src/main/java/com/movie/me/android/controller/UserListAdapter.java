package com.movie.me.android.controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.me.android.R;
import com.movie.me.android.domain.Movie;
import com.movie.me.android.domain.User;
import com.movie.me.android.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 * Created by hargueta on 11/20/16.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private Context context;
    private List<User> userResultList;
    private LayoutInflater layoutInflater;

    public UserListAdapter(Context context, List<User> userResultList) {
        this.context = context;
        this.userResultList = userResultList;
        this.layoutInflater = LayoutInflater.from(this.context);
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = layoutInflater.inflate(R.layout.single_user_result, parent, false);
        UserViewHolder userHolder = new UserListAdapter.UserViewHolder(userView);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User currUser = userResultList.get(position);
        Picasso.with(context).load(currUser.getPhotoURI()).transform(new RoundedTransformation()).into(holder.userProfileImage);
        holder.name.setText(currUser.getName());
    }

    @Override
    public int getItemCount() {
        return userResultList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView userProfileImage;
        TextView name;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.userProfileImage = (ImageView) itemView.findViewById(R.id.user_profile_image);
            this.name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}