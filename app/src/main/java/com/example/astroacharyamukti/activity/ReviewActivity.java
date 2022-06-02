package com.example.astroacharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.adapter.ReviewAdapter;
import com.example.astroacharyamukti.helper.Backend;
import com.example.astroacharyamukti.model.ReviewModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        review = findViewById(R.id.rating);
        overAllReview = findViewById(R.id.overAllRating);
        profile_image = findViewById(R.id.ratingProfileImage);
        getUserProfile();
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
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
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
        }, error -> {
            pDialog.dismiss();
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", "40072");
                return params;

            }
        };
        requestQueue.add(stringRequest);
    }

    private void getUserProfile() {
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/total-rating.php";
        String dataUrl = String.format(url, userId);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.......Please wait");
        progressDialog.show();
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dataUrl, response -> {
            Log.d("url", url);
            progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String profileImage = jsonObject.getString("image");
                String rating = jsonObject.getString("overall");
                String total_rating = jsonObject.getString("total_rating");
                review.setText(rating);
                overAllReview.setText(total_rating);
                String url1 = "https://theacharyamukti.com/image/astro/" + profileImage;
                Glide.with(getApplicationContext()).load(url1).into(profile_image);

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(ReviewActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(ReviewActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", "40072");
                return params;
            }
        };
        request.add(stringRequest);
    }
}