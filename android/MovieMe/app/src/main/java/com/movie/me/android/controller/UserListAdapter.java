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
import com.movie.me.android.util.RecyclerViewClickSubscriber;
import com.movie.me.android.util.RoundedTransformation;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.support.v7.recyclerview.R.styleable.RecyclerView;

/**
 * Created by hargueta on 11/20/16.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> implements View.OnClickListener {
    private Context context;
    private List<User> userResultList;
    private LayoutInflater layoutInflater;
    private RecyclerViewClickSubscriber subscriber;

    public UserListAdapter(Context context, List<User> userResultList, RecyclerViewClickSubscriber subscriber) {
        this.context = context;
        this.userResultList = userResultList;
        this.layoutInflater = LayoutInflater.from(this.context);
        this.subscriber = subscriber;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View userView = layoutInflater.inflate(R.layout.single_user_result, parent, false);
        userView.setOnClickListener(this);
        UserViewHolder userHolder = new UserListAdapter.UserViewHolder(userView);
        return userHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User currUser = userResultList.get(position);
        try {
            Picasso.with(context).load(currUser.getPhotoURI())
                    .transform(new RoundedTransformation()).into(holder.userProfileImage);
        } catch(Exception e) {
            Picasso.with(context).load(R.mipmap.ic_launcher)
                    .transform(new RoundedTransformation()).into(holder.userProfileImage);
        }
        holder.username.setText(currUser.getName());
        holder.name.setText(currUser.getName());
    }

    @Override
    public int getItemCount() {
        return userResultList.size();
    }

    @Override
    public void onClick(View view) {
        subscriber.notifyClick(view);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView userProfileImage;
        TextView username;
        TextView name;

        public UserViewHolder(View itemView) {
            super(itemView);
            this.userProfileImage = (ImageView) itemView.findViewById(R.id.user_profile_image);
            this.username = (TextView) itemView.findViewById(R.id.username);
            this.name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}