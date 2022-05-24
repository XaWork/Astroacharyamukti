package com.example.astroacharyamukti.ui.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class UpdateBankDetails extends Fragment implements View.OnClickListener {
    EditText beFe_name, bank_name, acc_num, acc_type, ifsc_code, branch_name, bank_address, pan_card;
    String beneficiaryName, bankName, accountNumber, accountType, ifscCode, branchName, bankAddress, panCard;

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
        bank_address = view.findViewById(R.id.et_branch_address);
        pan_card = view.findViewById(R.id.etPan_card_number);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home);
        getActivity().finish();
        return super.onOptionsItemSelected(item);
    }

    private void postData() {
        String url = "https://theacharyamukti.com/appapi/items/update.php";
        ProgressDialog pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    // showDialog();
                    jsonObject.getString("id");
                    Intent intent=new Intent(getActivity(), HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(getActivity(), "Success", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                pDialog.dismiss();

            }
        }) {
            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               // params.put("mobile"Bu, mobileNo);
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