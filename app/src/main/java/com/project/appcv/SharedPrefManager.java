package com.project.appcv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.project.appcv.DTO.UserPref;
import com.project.appcv.View.LoginActivity;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "my_shared_pref";
    private static final String KEY_JWT_TOKEN = "jwt_token";
    private static final String KEY_ROLE = "role";
    private static final String KEY_ID = "id";


    private static SharedPrefManager instance;
    private Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (instance == null) {
            instance = new SharedPrefManager(context);
        }
        return instance;
    }
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_JWT_TOKEN, null) != null;
    }

    public void saveJwtToken(String jwtToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_JWT_TOKEN, jwtToken);
        editor.apply();
    }

    public String getJwtToken() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_JWT_TOKEN, null);
    }

    public void saveRole(String role) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }

    public String getRole() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ROLE, null);
    }
    public void saveID(String id) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, id);
        editor.apply();
    }

    public String getID() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_ID, null);
    }
    public void clear() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
