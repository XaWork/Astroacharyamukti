package com.example.astroacharyamukti.activity;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.adapter.EarningAdapter;
import com.example.astroacharyamukti.helper.Backend;
import com.example.astroacharyamukti.model.EarnDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyEarningActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<EarnDetails> earningData = new ArrayList<>();
    ProgressBar progressBar;
    TextView total;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);
        getData();
        total = findViewById(R.id.total);
//        String getTotalBalance = Backend.getInstance(this).getTotal();
//        total.setText(getTotalBalance);
        recyclerView = findViewById(R.id.recyclerView_earning);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        progressBar = findViewById(R.id.progressBarNew);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        getData();
        super.onStart();
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    @Override
    protected void onRestart() {
        getData();
        super.onRestart();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/earned.php?acharid=%s";
        String data = String.format(url, userId);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, data, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.INVISIBLE);
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr = obj.getJSONArray("body");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jo = arr.getJSONObject(i);
                        EarnDetails earnData = new EarnDetails(
                                jo.getString("consultation_id"),
                                jo.getString("name"),
                                jo.getString("consultation_type"),
                                jo.getString("earamount"),
                                jo.getString("amount"),
                                jo.getString("duration"),
                                jo.getString("date"));
                        earningData.add(earnData);
                    }
                    String totalBal = obj.getString("total");
                    total.setText(totalBal);
                    Backend.getInstance(getApplicationContext()).saveTotal(totalBal);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                EarningAdapter earningAdapter = new EarningAdapter(getApplicationContext(), earningData);
                earningAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(earningAdapter);
            }
        }, error -> {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(MyEarningActivity.this, "Error", Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(stringRequest);
    }
}
