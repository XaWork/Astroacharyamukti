package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.astroacharyamukti.R;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luncher);
        new Timer().schedule(new TimerTask() {
            public void run() {
                startActivity(new Intent(Launcher.this, OtpActivity.class));
            }
        }, 3000);
    }
}