package com.acharyamukti.astrologer.activity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.acharyamukti.astrologer.helper.Backend;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.R;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    TextView review, bankDetails, changePassword,
            update_number, profile_name, profile_email,
            profile_number, oldPassword, newPassword;
    CircleImageView profile_image;
    String userId, oldNumber, newMobileNumber, getOlPassword, getNewPassword;
    EditText oldNum, newNumber;
    Button update;
    Dialog dialog;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
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
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getUser();
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
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.change_password);
        oldPassword = dialog.findViewById(R.id.oldPassword);
        newPassword = dialog.findViewById(R.id.newPassword);
        String oldPass = Backend.getInstance(this).getPassword();
        oldPassword.setText(oldPass);
        Button update = dialog.findViewById(R.id.updatePassword);
        update.setOnClickListener(view -> {
            changePassword();
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }

    private void fqeDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.update_mobile_number);
        oldNum = dialog.findViewById(R.id.etNumberUpdate);
        String mobile = Backend.getInstance(this).getMobileNumber();
        oldNum.setText(mobile);
        newNumber = dialog.findViewById(R.id.etNewNumberUpdate);
        update = dialog.findViewById(R.id.btnUpdate);
        oldNumber = oldNum.getText().toString();
        newMobileNumber = newNumber.getText().toString().trim();
        update.setOnClickListener(view -> {
            if (oldNumber != null || newMobileNumber != null) {
                updateMobile();
            } else {
                Toast.makeText(UserProfile.this, "Number is not valid", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void updateMobile() {
        newMobileNumber = newNumber.getText().toString().trim();
        userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/update-mobile.php?acharid=%s&mobile=%s";
        String dataUrl = String.format(url, userId, newMobileNumber);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.......Please wait");
        progressDialog.show();
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dataUrl, response -> {
            progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                if (status.equals("true")) {
                    Toast.makeText(UserProfile.this, status, Toast.LENGTH_SHORT).show();

                    dialog.dismiss();
                } else {
                    Toast.makeText(UserProfile.this, status, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();

        });

        request.add(stringRequest);

    }

    private void getUser() {
        progressBar.setVisibility(View.VISIBLE);
        userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/profile.php?acharid=%s";
        String dataUrl = String.format(url, userId);
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl, response -> {
            Log.d("url", url);
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                jsonObject.getString("status");
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
                    String url1 = "https://theacharyamukti.com/image/astro/" + profileImage;
                    Glide.with(getApplicationContext()).load(url1).into(profile_image);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                }
                Backend.getInstance(getApplicationContext()).saveMobile(userNumber);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(UserProfile.this, error.toString(), Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getHeaders() {
                return new HashMap<>();
            }
        };
        request.add(stringRequest);
    }

    private void changePassword() {
        userId = Backend.getInstance(this).getUserId();
        getOlPassword = oldPassword.getText().toString();
        getNewPassword = newPassword.getText().toString();
        String url = "https://theacharyamukti.com/managepanel/apis/change-password.php";
        String dataUrl = String.format(url, userId, getOlPassword, getNewPassword);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.......Please wait");
        progressDialog.show();
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                Toast.makeText(UserProfile.this, "True", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
                //    Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(UserProfile.this, "Error", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                params.put("oldpassword", getOlPassword);
                params.put("newpassword", getNewPassword);
                return params;
            }


        };
        request.add(stringRequest);

    }
}
