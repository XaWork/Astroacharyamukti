package com.example.astroacharyamukti.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class Backend {
    private AssetManager assetManager;
    private Gson gson = new Gson();
    private SharedPreferences preferences;
    private static Backend instance;

    private Backend(Context context) {
        assetManager = context.getAssets();
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static Backend getInstance(Context context) {
        return instance == null ? instance = new Backend(context) : instance;
    }

    public String getUserId() {
        return preferences.getString("reg_id", "");
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("reg_id", userId);
        editor.apply();
    }

    public String getMobileNumber() {
        return preferences.getString("mobile", "");
    }

    public void saveMobile(String mobile) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("mobile", mobile);
        editor.apply();
    }

    public void saveTotal(String total) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("total", total);
        editor.apply();
    }

    public String getTotal() {
        return preferences.getString("total", "");
    }

    public String getPassword() {

        return preferences.getString("password", "");
    }

    public void savePassword(String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("password", password);
        editor.apply();
    }

    public String getUserName() {
        return preferences.getString("email", "");
    }

    public void saveUserName(String username) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("email", username);
        editor.apply();
    }

    public String getUserStatus() {
        return preferences.getString("status", "");
    }

    public void saveUserStatus(String status) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("status", "");
        editor.apply();
    }
}
