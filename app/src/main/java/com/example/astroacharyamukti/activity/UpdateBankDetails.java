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

public class UpdateBankDetails extends AppCompatActivity {
    TextView signIn, forgotPassword;
    EditText email, password;
    Button login, btnLogin_otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_actvity);
//        signIn = findViewById(R.id.signup);
//        forgotPassword = findViewById(R.id.txtPass);
//        email = findViewById(R.id.etEmail);
//        password = findViewById(R.id.etPassword);
//        login = findViewById(R.id.btnLogin);
//        btnLogin_otp = findViewById(R.id.btnLogin_otp);
    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

//    public void showDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.dailog_forgot_password);
//        Button send = dialog.findViewById(R.id.btnSend);
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText email = dialog.findViewById(R.id.etEmailLink);
//                if (email.getText().toString().length() < 1) {
//                    Toast.makeText(getApplicationContext(), "Enter the register Email id", Toast.LENGTH_LONG).show();
//
//                } else {
//                    dialog.dismiss();
//                }
//            }
//        });
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//
//    }

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