package com.example.astroacharyamukti.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.astroacharyamukti.R;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView english = findViewById(R.id.language_english);
        english.setOnClickListener(this);
        TextView hindi = findViewById(R.id.language_hindi);
        hindi.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) ;
        finish();
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.language_english:
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.language_hindi:
                Intent hindi = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(hindi);
                hindi.putExtra("", 1);
                break;
        }
    }
}