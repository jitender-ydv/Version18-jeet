package com.example.abhishek.version_18.other;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.abhishek.version_18.LoginActivity;
import com.example.abhishek.version_18.model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "version_18_user";
    private static final String KEY_NAME = "name";
    private static final String KEY_CLGNAME = "clgname";
    private static final String KEY_TNAME = "tname";
    private static final String KEY_GNAME = "gname";
    private static final String KEY_GPHONE = "gphone";
    private static final String KEY_GGENDER = "ggender";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CONTACT= "contact";

    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_CLGNAME, user.getClgname());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_CONTACT, user.getContact());
        editor.putString(KEY_TNAME,user.getTname());
        editor.putString(KEY_GNAME,user.getGname());
        editor.putString(KEY_GPHONE,user.getGphone());
        editor.putString(KEY_GGENDER,user.getGgender());

        editor.apply();
    }

    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_CLGNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_CLGNAME,null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_CONTACT, null),
                sharedPreferences.getString(KEY_TNAME,null),
                sharedPreferences.getString(KEY_GNAME,null),
                sharedPreferences.getString(KEY_GPHONE,null),
                sharedPreferences.getString(KEY_GGENDER,null)
        );

    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }

}
