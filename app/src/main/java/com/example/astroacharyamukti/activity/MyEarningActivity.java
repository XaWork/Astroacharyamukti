package com.example.astroacharyamukti.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.adapter.EarningAdapter;

public class MyEarningActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earning);
        recyclerView=findViewById(R.id.recyclerView_earning);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        EarningAdapter earningAdapter=new EarningAdapter(getApplicationContext());
        recyclerView.setAdapter(earningAdapter);
    }
}
