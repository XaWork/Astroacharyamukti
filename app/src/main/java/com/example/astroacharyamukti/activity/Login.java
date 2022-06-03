package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.helper.Backend;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button sighIn;
    EditText etEmail, password;
    TextView signOut;
    CheckBox check_box_condition;
    TextView termAndCondition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        etEmail = findViewById(R.id.etEmail);
        sighIn = findViewById(R.id.sendOtp);
        sighIn.setOnClickListener(this);
        password = findViewById(R.id.etPassword);
        signOut = findViewById(R.id.signIn);
        signOut.setOnClickListener(this);
        check_box_condition = findViewById(R.id.check_box_condition);
        termAndCondition = findViewById(R.id.termAndCondition);
        termAndCondition.setOnClickListener(this);
        String userName = Backend.getInstance(this).getUserName();
        String pass = Backend.getInstance(this).getPassword();
        etEmail.setText(userName);
        password.setText(pass);
    }

    public void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dailog_forgot_password);
        Button send = dialog.findViewById(R.id.btnSend);
        send.setOnClickListener(view -> {
            EditText email = dialog.findViewById(R.id.etEmailLink);
            if (email.getText().toString().length() < 1) {
                Toast.makeText(getApplicationContext(), "Enter the register Email id", Toast.LENGTH_LONG).show();

            } else {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sendOtp:
                if ((etEmail.getText().toString().length() < 1) || (password.getText().toString().length() < 1)) {
                    Toast.makeText(this, "Enter your Email Id & Password", Toast.LENGTH_SHORT).show();
                } else {
                    if (check_box_condition.isChecked()) {
                        postData();
                    } else {
                        Toast.makeText(getApplicationContext(),"Please accepts terms and condition", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.text_forgot_password:
                showDialog();
                break;
            case R.id.signIn:
                String theUrl = "https://theacharyamukti.com/registration.php"; // missing 'http://' will cause crashed
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(theUrl));
                startActivity(i);
                break;
            case R.id.termAndCondition:
                String termConditionUrl = "https://theacharyamukti.com/terms-and-conditions.php";
                Intent terms = new Intent(Intent.ACTION_VIEW);
                terms.putExtra("", "1");
                terms.setData(Uri.parse(termConditionUrl));
                startActivity(terms);
                break;
        }

    }

    @Override
    public void onBackPressed() {
        this.finish();
        super.onBackPressed();
    }

    private void postData() {
        String url = "https://theacharyamukti.com/managepanel/apis/login.php";
        String email = etEmail.getText().toString();
        String pass = password.getText().toString();
        Backend.getInstance(this).savePassword(pass);
        Backend.getInstance(this).saveUserName(email);
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            pDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String userId = jsonObject.getString("reg_id");
                String msg = jsonObject.getString("msg");
                if (jsonObject.getString("status").equals("true")) {
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
                }
                Backend.getInstance(getApplicationContext()).saveUserId(userId);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(Login.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            pDialog.dismiss();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", pass);
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

}