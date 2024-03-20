package com.acharyamukti.astrologer.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.astrologer.api.RetrofitClient;
import com.acharyamukti.astrologer.helper.Backend;
import com.acharyamukti.astrologer.model.BankData;
import com.acharyamukti.astrologer.ui.fragment.UpdateBankDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetails extends AppCompatActivity implements View.OnClickListener {
    String id, bfName, bank_name, account_no, account_Type, ifsc, branch_name, bank_address, pan, gstn;
    TextView bfname, bank, account, type, ifsc1, branch, address, pan1, gst;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch_actvity);
        Button edit = findViewById(R.id.btn_edit);
        edit.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewBankDetails);
        LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layout);
        //getDetails();
        bfname = findViewById(R.id.txtName);
        bank = findViewById(R.id.txtAnyBank);
        account = findViewById(R.id.txt_acc_number);
        type = findViewById(R.id.txt_acc_type);
        ifsc1 = findViewById(R.id.txt_ifsc_number);
        branch = findViewById(R.id.txt_bank_location);
        address = findViewById(R.id.txt_bank_address);
        pan1 = findViewById(R.id.txt_pan_card_number);
        gst = findViewById(R.id.txt_gst_number);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        getBankDetails();
    }

    private void getDetails() {
        List<BankData> dataList = new ArrayList<>();
        String userid = Backend.getInstance(this).getUserId();
        Call<BankData> call = RetrofitClient.getInstance().getApi().getBankDetails(userid);
        call.enqueue(new Callback<BankData>() {
            @Override
            public void onResponse(@NonNull Call<BankData> call, @NonNull Response<BankData> response) {
                if (response.isSuccessful()) {
                    //BankDetailsAdapter bankDetailsAdapter = new BankDetailsAdapter(getApplicationContext(), dataList);
                    //recyclerView.setAdapter(bankDetailsAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<BankData> call, @NonNull Throwable t) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
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
        progressBar.setVisibility(View.VISIBLE);
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/appapi/items/read.php?acharid=%s";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        Log.e("bank", "Url : "+url + "\nUser id"+userId);
        String data = String.format(url, userId);
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.GET, data, null, response -> {
            progressBar.setVisibility(View.GONE);
            try {
                JSONArray itemsArray = response.getJSONArray("Bank_Details");
                for (int i = 0; i < itemsArray.length(); i++) {

                    Log.e("bank", itemsArray.get(0).toString());

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
                    gstn = jsonObject.getString("gst");

                    Log.e("bank", "id : "+jsonObject.getInt("id"));

                    bfname.setText(bfName);
                    gst.setText(gstn);
                    bank.setText(bank_name);
                    account.setText(account_no);
                    type.setText(account_Type);
                    ifsc1.setText(ifsc);
                    branch.setText(branch_name);
                    address.setText(bank_address);
                    pan1.setText(pan);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                }
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
        }, error -> {
            progressBar.setVisibility(View.GONE);
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                return params;
            }
        };
        requestQueue.add(jsonobj);
    }

}