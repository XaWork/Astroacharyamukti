package com.example.astroacharyamukti.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.activity.HomeActivity;
import com.example.astroacharyamukti.activity.Login;
import com.example.astroacharyamukti.helper.Backend;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UpdateBankDetails extends Fragment implements View.OnClickListener {
    EditText beFe_name, bank_name, acc_num, acc_type, ifsc_code, branch_name, bank_address, pan_card;
    String beneficiaryName, bankName, accountNumber, accountType, ifscCode, branchName, bankAddress, panCard;
    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_bank_details, container, false);
        Button btnUpdate = view.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        beFe_name = view.findViewById(R.id.etBeFe_account);
        bank_name = view.findViewById(R.id.etBank_name);
        acc_num = view.findViewById(R.id.etAccount_number);
        acc_type = view.findViewById(R.id.etAcc_type);
        ifsc_code = view.findViewById(R.id.etifsc_number);
        branch_name = view.findViewById(R.id.etBranch_name);
        bank_address = view.findViewById(R.id.etBank_address);
        pan_card = view.findViewById(R.id.etPan_card_number);
        progressBar = view.findViewById(R.id.progressBar);
        if (getArguments() != null) {
            beneficiaryName = getArguments().getString("bfname");
            bankName = getArguments().getString("bank_name");
            accountNumber = getArguments().getString("account_no");
            accountType = getArguments().getString("account_type");
            ifscCode = getArguments().getString("ifsc");
            branchName = getArguments().getString("branch_bame");
            bankAddress = getArguments().getString("bank_bddress");
            panCard = getArguments().getString("pan");
        }
        beFe_name.setText(beneficiaryName);
        bank_name.setText(bankName);
        acc_num.setText(accountNumber);
        acc_type.setText(accountType);
        ifsc_code.setText(ifscCode);
        branch_name.setText(branchName);
        bank_address.setText(bankAddress);
        pan_card.setText(panCard);
        progressBar.setVisibility(View.INVISIBLE);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        getActivity().finish();
        return super.onOptionsItemSelected(item);
    }

    private void postData() {
        String userId = Backend.getInstance(getActivity()).getUserId();
        String url = "https://theacharyamukti.com/astrologer/acc-update.php";
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String status = jsonObject.getString("status");
                    if (status.equals("true")) {
                        Intent intent = new Intent(getActivity(), HomeActivity.class);
                        startActivity(intent);
                        progressBar.setVisibility(View.INVISIBLE);
                    } else {
                        progressBar.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                params.put("bfname", beneficiaryName = beFe_name.getText().toString());
                params.put("bank_name", bankName = bank_name.getText().toString());
                params.put("acc_no", accountNumber = acc_num.getText().toString());
                params.put("acc_type", accountType = acc_type.getText().toString());
                params.put("ifsc", ifscCode = ifsc_code.getText().toString());
                params.put("branch_name", branchName = branch_name.getText().toString());
                params.put("bank_add", bankAddress = bank_address.getText().toString());
                params.put("pan", panCard = pan_card.getText().toString());
                return params;
            }
        };
        requestQueue.add(stringRequest);

    }

    @Override
    public void onClick(View v) {
        postData();
    }
}