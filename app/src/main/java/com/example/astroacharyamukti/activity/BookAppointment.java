package com.example.astroacharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.ui.fragment.AboutFragment;
import com.example.astroacharyamukti.ui.fragment.ChatFragment;

public class BookAppointment extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.layout, new AboutFragment()).commit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.radio0:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout,
                                new AboutFragment()).commit();
                break;
            case R.id.radio1:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout,
                                new ChatFragment()).commit();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        Log.d("Register", "restart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("Register", "start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("Register", "stop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("Register", "resume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("Register", "destroy");
        super.onDestroy();

    }
}