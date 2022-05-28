package com.example.astroacharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

public class MyEarningActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<EarnDetails> earningData = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);
        getData();
        TextView total = findViewById(R.id.total);
        String getTotalBalance = Backend.getInstance(this).getTotal();
        total.setText(getTotalBalance);
        recyclerView = findViewById(R.id.recyclerView_earning);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        String url = "https://theacharyamukti.com/managepanel/apis/earned.php?acharid=40072";
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                pDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    JSONArray arr = obj.getJSONArray("body");
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject jo = arr.getJSONObject(i);
                        EarnDetails earnData = new EarnDetails(
                                jo.getString("consultation_id"),
                                jo.getString("nqme"),
                                jo.getString("consultation_type"),
                                jo.getString("earamount"),
                                jo.getString("amount"),
                                jo.getString("duration"),
                                jo.getString("date"));
                        earningData.add(earnData);
                    }
                    String total = obj.getString("total");
                    Backend.getInstance(getApplicationContext()).saveTotal(total);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                EarningAdapter earningAdapter = new EarningAdapter(getApplicationContext(), earningData);
                earningAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(earningAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(MyEarningActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
