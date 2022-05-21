package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button sendOtp;
    EditText etMobile;
    TextView sighIn, forgot_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        sendOtp = findViewById(R.id.sendOtp);
        sendOtp.setOnClickListener(this);
        sighIn = findViewById(R.id.signIn);
        forgot_password = findViewById(R.id.text_forgot_password);
        forgot_password.setOnClickListener(this);

    }

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dailog_forgot_password);
        Button send = dialog.findViewById(R.id.btnSend);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText email = dialog.findViewById(R.id.etEmailLink);
                if (email.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Enter the register Email id", Toast.LENGTH_LONG).show();

                } else {
                    dialog.dismiss();
                }
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

//    public void showDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.virification_otp_layout);
//        Button verify = dialog.findViewById(R.id.btnVerify);
//        verify.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                EditText otp = dialog.findViewById(R.id.etOtp);
//                if (otp.getText().toString().length() < 1) {
//                    Toast.makeText(getApplicationContext(), "Enter OTP", Toast.LENGTH_LONG).show();
//
//                } else {
//                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                    startActivity(intent);
//                    dialog.dismiss();
//                }
//            }
//        });
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendOtp:
                EditText email = findViewById(R.id.etEmail);
                EditText password = findViewById(R.id.etPassword);
                if ((email.getText().toString().length() < 1) || password.getText().toString().length() < 1) {
                    Toast.makeText(this, "Enter your Email Id & Password", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.text_forgot_password:
                showDialog();
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private void postData() {
        String url = "https://theacharyamukti.com/appapi/conection.php";
        String mobileNo = etMobile.getText().toString();
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    showDialog();
                    Toast.makeText(Login.this, "Success", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
                pDialog.dismiss();

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("mobile", mobileNo);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

}