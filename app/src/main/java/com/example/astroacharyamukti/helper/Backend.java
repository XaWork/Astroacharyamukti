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
        return preferences.getString("userId", "");
    }

    public void saveUserId(String userId) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userId", userId);
        editor.apply();
    }

    public int getDate() {
        return preferences.getInt("date", 1);
    }

    public void saveDate(int date) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("date", date);
        editor.apply();
    }
}
