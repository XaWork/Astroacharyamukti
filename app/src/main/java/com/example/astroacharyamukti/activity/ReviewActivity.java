package com.example.astroacharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.adapter.MyFragmentPagerAdapter;
import com.example.astroacharyamukti.adapter.ReviewAdapter;
import com.google.android.material.tabs.TabLayout;

public class ReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_review);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ReviewAdapter reviewAdapter = new ReviewAdapter(getApplicationContext());
        recyclerView.setAdapter(reviewAdapter);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }
}