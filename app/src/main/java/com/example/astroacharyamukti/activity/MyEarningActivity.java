package com.example.astroacharyamukti.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
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
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.adapter.EarningAdapter;
import com.example.astroacharyamukti.model.EarnDetails;

import org.json.JSONArray;
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
        recyclerView = findViewById(R.id.recyclerView_earning);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getEarningList() {
        String url = "https://theacharyamukti.com/managepanel/apis/earned.php";
        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...PLease wait");
        pDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                pDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.getString("total");
                    JSONArray itemsArray = response.getJSONArray("Bank_Details");
                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject jsonObject1 = itemsArray.getJSONObject(i);
                        EarnDetails earnData = new EarnDetails(
                                jsonObject1.getString("consultation_id"),
                                jsonObject1.getString("consultation_type"),
                                jsonObject1.getString("earamount"),
                                jsonObject1.getString("amount"),
                                jsonObject.getString("duration"),
                                jsonObject1.getString("date")

                        );
                        earningData.add(earnData);
                    }


                } catch (
                        Exception e) {
                    e.printStackTrace();
                }
                EarningAdapter earningAdapter = new EarningAdapter(getApplicationContext(),earningData);
                recyclerView.setAdapter(earningAdapter);
                earningAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
