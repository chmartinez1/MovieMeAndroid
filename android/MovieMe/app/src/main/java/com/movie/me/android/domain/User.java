package com.movie.me.android.domain;

/**
 * Created by hargueta on 11/20/16.
 */
import java.util.Set;

public class User {
    private Long id;

    private String userid;

    private String name;

    private String photoURI;

    private String age;

    private String email;

    private String username;

    private Set<Movie> moviesLiked;

    private Set<User> friends;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userid;
    }

    public void setUserId(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Movie> getMoviesLiked() {
        return moviesLiked;
    }

    public void setMoviesLiked(Set<Movie> moviesLiked) {
        this.moviesLiked = moviesLiked;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhotoURI() {
        return photoURI;
    }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public String getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.email;
    }
}