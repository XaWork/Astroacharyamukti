package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.astroacharyamukti.R;

public class Register extends AppCompatActivity implements View.OnClickListener {
    TextView username, email, password, confirmPassword, mobile;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.etUsername);
        email = findViewById(R.id.etRegister_email);
        password = findViewById(R.id.etRegister_password);
        confirmPassword = findViewById(R.id.etRegister_conf_pass);
        mobile = findViewById(R.id.etRegister_mobile);
        signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

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

    @Override
    public void onClick(View view) {
        if ((username.getText().toString().length() < 1)
                || (email.getText().toString().length() < 1)
                || (password.getText().toString().length() < 1)
                || (mobile.getText().toString().length() < 1)
                || (confirmPassword.getText().toString().length() < 1)) {
            Toast.makeText(getApplicationContext(), "Please enter all details", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent(getApplicationContext(), UpdateBankDetails.class);
            startActivity(intent);
        }

    }
}