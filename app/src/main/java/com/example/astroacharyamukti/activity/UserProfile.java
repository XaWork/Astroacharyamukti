package com.example.astroacharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.helper.Backend;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    TextView review, bankDetails, changePassword,
            update_number, profile_name, profile_email,
            profile_number;
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getUserProfile();
        review = findViewById(R.id.reviewCustomer);
        review.setOnClickListener(this);
        bankDetails = findViewById(R.id.bank_details);
        bankDetails.setOnClickListener(this);
        changePassword = findViewById(R.id.changePassword);
        changePassword.setOnClickListener(this);
        update_number = findViewById(R.id.update_number);
        update_number.setOnClickListener(this);
        profile_name = findViewById(R.id.profile_name);
        profile_email = findViewById(R.id.profile_email);
        profile_number = findViewById(R.id.profile_number);
        profile_image = findViewById(R.id.profileImage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reviewCustomer:
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);
                break;
            case R.id.bank_details:
                Intent bank = new Intent(getApplicationContext(), BankDetails.class);
                startActivity(bank);
                break;
            case R.id.changePassword:
                showDialog();
                break;
            case R.id.update_number:
                fqeDialog();
                break;
        }

    }

    private void showDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.change_password);
        TextView custom_cust_review = dialog.findViewById(R.id.custom_cust_review);
        TextView text_update_no = dialog.findViewById(R.id.text_update_details);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void fqeDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_help_and_support);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void getUserProfile() {
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/profile.php";
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.......Please wait");
        progressDialog.show();
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    jsonObject.getString("reg_id");
                    String profileImage = jsonObject.getString("image");
                    String emailId = jsonObject.getString("email");
                    String userNumber = jsonObject.getString("mobile");
                    String userName = jsonObject.getString("name");
                    String msg = jsonObject.getString("msg");
                    if (msg.equals("Successfull")) {
                        profile_email.setText(emailId);
                        profile_name.setText(userName);
                        profile_number.setText(userNumber);
                        String url = "https://theacharyamukti.com/image/astro/" + profileImage;
                        Glide.with(getApplicationContext()).load(url).into(profile_image);
                    } else {
                        Toast.makeText(UserProfile.this, status, Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                return params;
            }
        };
        request.add(stringRequest);
    }
}