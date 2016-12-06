package com.movie.me.android.domain;

/**
 * Created by hargueta on 11/20/16.
 */
import java.util.Set;

public class User {
    private String USERID;

    private String NAME;

    private String PHOTO_URI;

    private String AGE;

    private String EMAIL;

    public String getUserId() {
        return USERID;
    }

    public void setUserId(String userid) {
        this.USERID = userid;
    }

    public String getName() {
        return NAME;
    }

    public void setName(String name) {
        this.NAME = name;
    }

    public String getEmail() {
        return EMAIL;
    }

    public void setEmail(String email) {
        this.EMAIL = email;
    }

    public String getUserid() {
        return USERID;
    }

    public void setUserid(String userid) {
        this.USERID = userid;
    }

    public String getPhotoURI() {
        return PHOTO_URI;
    }

    public void setPhotoURI(String photoURI) {
        this.PHOTO_URI = photoURI;
    }

    public String getAge() {
        return AGE;
    }

    public void setAge(String age) {
        this.AGE = age;
    }

    @Override
    public String toString() {
        return this.EMAIL;
    }
}