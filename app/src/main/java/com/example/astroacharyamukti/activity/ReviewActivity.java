package com.example.astroacharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.adapter.EarningAdapter;
import com.example.astroacharyamukti.adapter.MyFragmentPagerAdapter;
import com.example.astroacharyamukti.adapter.ReviewAdapter;
import com.example.astroacharyamukti.helper.Backend;
import com.example.astroacharyamukti.model.EarnDetails;
import com.example.astroacharyamukti.model.ReviewModel;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReviewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ReviewModel> reviewModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getCustomerReview();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getCustomerReview() {
        String url = "https://theacharyamukti.com/managepanel/apis/review.php";
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
                        ReviewModel reviewModel = new ReviewModel(
                                jo.getString("review_id"),
                                jo.getString("call_id"),
                                jo.getString("name"),
                                jo.getString("comment"),
                                obj.getString("rating"),
                                jo.getString("reply"),
                                jo.getString("reply_date"),
                                jo.getString("date"));
                        reviewModels.add(reviewModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                ReviewAdapter reviewAdapter = new ReviewAdapter(getApplicationContext(), reviewModels);
                reviewAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(reviewAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", "40072");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}