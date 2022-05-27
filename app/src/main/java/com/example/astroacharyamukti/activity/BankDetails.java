package com.example.astroacharyamukti.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.ui.fragment.AboutFragment;
import com.example.astroacharyamukti.ui.fragment.UpdateBankDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BankDetails extends AppCompatActivity implements View.OnClickListener {
    String id, bfName, bank_name, account_no, account_Type, ifsc, branch_name, bank_address, pan;
    TextView bfname, bank, account, type, ifsc1, branch, address, pan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_actvity);
        Button edit = findViewById(R.id.btn_edit);
        edit.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        bfname = findViewById(R.id.txtName);
        bank = findViewById(R.id.txtAnyBank);
        account = findViewById(R.id.txt_acc_number);
        type = findViewById(R.id.txt_acc_type);
        ifsc1 = findViewById(R.id.txt_ifsc_number);
        branch = findViewById(R.id.txt_bank_location);
        address = findViewById(R.id.txt_bank_address);
        pan1 = findViewById(R.id.txt_pan_card_number);
        TextView gst = findViewById(R.id.txt_gst_number);

        getBankDetails();

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

    @Override
    public void onClick(View v) {
        Fragment fragment = new UpdateBankDetails();
        Bundle bundle = new Bundle();
        bundle.putString("bfname", bfName);
        bundle.putString("bank_name", bank_name);
        bundle.putString("account_no", account_no);
        bundle.putString("account_type", account_Type);
        bundle.putString("ifsc", ifsc);
        bundle.putString("branch_bame", branch_name);
        bundle.putString("bank_bddress", bank_address);
        bundle.putString("pan", pan);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.framAdd, fragment).commit();
        fragment.setArguments(bundle);
    }

    private void getBankDetails() {
        String url = "https://theacharyamukti.com/appapi/items/read.php";
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                try {
                    JSONArray itemsArray = response.getJSONArray("Bank_Details");
                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject jsonObject = itemsArray.getJSONObject(i);
                        jsonObject.getInt("id");
                        bfName = jsonObject.getString("bfname");
                        bank_name = jsonObject.getString("bank_name");
                        account_no = jsonObject.getString("account_no");
                        account_Type = jsonObject.getString("account_type");
                        ifsc = jsonObject.getString("ifsc");
                        branch_name = jsonObject.getString("branch_bame");
                        bank_address = jsonObject.getString("bank_bddress");
                        pan = jsonObject.getString("pan");
                        String gst = jsonObject.getString("gst");
                        bfname.setText(bfName);
                        bank.setText(bank_name);
                        account.setText(account_no);
                        type.setText(account_Type);
                        ifsc1.setText(ifsc);
                        branch.setText(branch_name);
                        address.setText(bank_address);
                        pan1.setText(pan);
                        Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                    }


                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }

}