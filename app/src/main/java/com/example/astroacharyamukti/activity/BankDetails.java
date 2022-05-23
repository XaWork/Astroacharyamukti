package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astroacharyamukti.R;

public class BankDetails extends AppCompatActivity {
    TextView signIn, forgotPassword;
    EditText email, password;
    Button login, btnLogin_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_actvity);

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        Log.d("Login", "restart");
        super.onRestart();
    }

    @Override
    protected void onStart() {
        Log.d("Login", "start");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d("Login", "stop");
        super.onStop();
    }

    @Override
    protected void onResume() {
        Log.d("Login", "resume");
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        Log.d("Login", "destroy");
        super.onDestroy();
    }
}