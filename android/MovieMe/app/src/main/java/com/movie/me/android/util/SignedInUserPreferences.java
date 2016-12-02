package com.movie.me.android.util;

import android.content.Context;

/**
 * Created by hargueta on 12/1/16.
 */

public final class SignedInUserPreferences {
    public static String SHARED_PREFERENCES_FILE_KEY = "user_preferences";
    public static String USER_ID_KEY = "userid";
    public static String USERNAME_KEY = "username";
    public static String EMAIL_KEY = "email";

    public static String getUserid(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
                .getString(USER_ID_KEY, null);
    }

    public static String getUsername(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
                .getString(USERNAME_KEY, null);
    }

    public static String getEmail(Context context) {
        return context.getSharedPreferences(SHARED_PREFERENCES_FILE_KEY, Context.MODE_PRIVATE)
                .getString(EMAIL_KEY, null);
    }
}
