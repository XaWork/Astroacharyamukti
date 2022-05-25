package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.astroacharyamukti.R;

public class UserProfile extends AppCompatActivity implements View.OnClickListener {
    TextView review, bankDetails, changePassword, update_number;

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
}