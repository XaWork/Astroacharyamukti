package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.helper.Backend;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        String userName = Backend.getInstance(this).getUserName();
        String password = Backend.getInstance(this).getPassword();
        ImageView image=findViewById(R.id.imageApp);
        Glide.with(this).load(R.drawable.logo5).into(image);
        new Timer().schedule(new TimerTask() {
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String storedUsername = prefs.getString("username", userName);
                String storedPassword = prefs.getString("password", password);
                if (storedUsername.equalsIgnoreCase(userName)&&storedPassword.equalsIgnoreCase(password)) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                }
            }
        }, 3000);
    }
}