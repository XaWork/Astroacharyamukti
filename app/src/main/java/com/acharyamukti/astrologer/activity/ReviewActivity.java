package com.acharyamukti.astrologer.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.acharyamukti.astrologer.adapter.ReviewAdapter;
import com.acharyamukti.astrologer.helper.Backend;
import com.acharyamukti.astrologer.model.ReviewModel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ReviewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<ReviewModel> reviewModels = new ArrayList<>();
    TextView review, overAllReview;
    CircleImageView profile_image;
    ProgressBar progressBar;
    String userId;
    int totalRatingCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        review = findViewById(R.id.rating);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        overAllReview = findViewById(R.id.overAllRating);
        profile_image = findViewById(R.id.ratingProfileImage);
        userId = Backend.getInstance(this).getUserId();
        //getUserProfile();
        //getCustomerReview();
    }

  /*  @Override
    protected void onStart() {
        getUserProfile();
        getCustomerReview();
        super.onStart();
    }*/

    @Override
    protected void onResume() {
        getUserProfile();
        getCustomerReview();
        super.onResume();
    }

   /* @Override
    protected void onRestart() {
        getUserProfile();
        getCustomerReview();
        super.onRestart();
    }*/

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    private void getCustomerReview() {
        String url = "https://theacharyamukti.com/managepanel/apis/review.php?acharid=%s";
        String dataUrl = String.format(url, userId);
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        progressBar.setVisibility(View.VISIBLE);
        @SuppressLint("NotifyDataSetChanged") StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl, response -> {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray arr = obj.getJSONArray("body");
                for (int i = 0; i < arr.length(); i++) {
                    totalRatingCount+=1;
                    JSONObject jo = arr.getJSONObject(i);
                    ReviewModel reviewModel = new ReviewModel(
                            jo.getString("review_id"),
                            jo.getString("call_id"),
                            jo.getString("name"),
                            jo.getString("comment"),
                            jo.getString("rating"),
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

            Log.e("review", "Reivew count is :"+totalRatingCount);
        }, error -> {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getUserProfile() {
        String url = "https://theacharyamukti.com/managepanel/apis/total-rating.php?acharid=%s";
        String dataUrl = String.format(url, userId);

        Log.e("review", "User id : "+userId);

        progressBar.setVisibility(View.VISIBLE);
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, dataUrl, response -> {
            try {
                JSONObject jsonObject = new JSONObject(response);
                String profileImage = jsonObject.getString("image");
                String rating = jsonObject.getString("overall");
                String total_rating = jsonObject.getString("total_rating");
                review.setText(rating);
                overAllReview.setText(total_rating);
                String url1 = "https://theacharyamukti.com/image/astro/" + profileImage;
                Glide.with(getApplicationContext()).load(url1).into(profile_image);
                progressBar.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ReviewActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(ReviewActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                return params;
            }
        };
        request.add(stringRequest);
    }
}