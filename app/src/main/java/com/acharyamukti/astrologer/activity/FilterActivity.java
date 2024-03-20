package com.acharyamukti.astrologer.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.acharyamukti.astrologer.adapter.EarningAdapter;
import com.acharyamukti.astrologer.helper.Backend;
import com.acharyamukti.astrologer.model.EarnDetails;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.databinding.ActivityFilterBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ActivityFilterBinding binding;
    List<EarnDetails> earningData = new ArrayList<>();
    String startDate, month;
    String endDate;
    float totalAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("Filter");

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select");
        categories.add("Jan");
        categories.add("Feb");
        categories.add("Mar");
        categories.add("Apr");
        categories.add("May");
        categories.add("Jun");
        categories.add("Jul");
        categories.add("Aug");
        categories.add("Sep");
        categories.add("Oct");
        categories.add("Nov");
        categories.add("Dec");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        binding.month.setAdapter(dataAdapter);

        binding.progressBarNew.setVisibility(View.GONE);
        binding.apply.setOnClickListener(this);
        binding.startDate.setOnClickListener(this);
        binding.endDate.setOnClickListener(this);
        // Spinner click listener
        binding.month.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String selectItem = parent.getItemAtPosition(position).toString();
        if(selectItem.equals("Select"))
            month = "";
        else
            month = selectItem;

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        month = "";
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        item.getItemId();
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startDate:
                startDatePicker();
                break;
            case R.id.endDate:
                endDatePicker();
                break;
            case R.id.apply: {
                getFilterData();
                break;
            }
        }
    }


    private void startDatePicker() {
        Calendar mCurrentDate = Calendar.getInstance();
        int year1 = mCurrentDate.get(Calendar.YEAR);
        int month1 = mCurrentDate.get(Calendar.MONTH);
        int day1 = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog mDatePicker = new DatePickerDialog(this, (datepicker, selectedYear, selectedMonth, selectedDay) -> {
            startDate = selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear;
            Log.e("Date Selected", "Month: " + (selectedMonth + 1) + " Day: " + selectedDay + " Year: " + selectedYear);
            binding.startDate.setText(selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear);
        }, year1, month1, day1);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }


    private void endDatePicker() {
        Calendar mCurrentDate = Calendar.getInstance();
        int year1 = mCurrentDate.get(Calendar.YEAR);
        int month1 = mCurrentDate.get(Calendar.MONTH);
        int day1 = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog mDatePicker = new DatePickerDialog(this, (datepicker, selectedYear, selectedMonth, selectedDay) -> {
            endDate = selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear;
            Log.e("Date Selected", "Month: " + (selectedMonth + 1) + " Day: " + selectedDay + " Year: " + selectedYear);
            binding.endDate.setText(selectedDay + "-" + (selectedMonth + 1) + "-" + selectedYear);
        }, year1, month1, day1);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    private void getFilterData() {
        totalAmount = 0;
        binding.progressBarNew.setVisibility(View.VISIBLE);
        String consultationId = binding.consultationId.getText().toString();
        String acharid = Backend.getInstance(this).getUserId();
        String name = binding.name.getText().toString();
        startDate = binding.startDate.getText().toString();
        endDate = binding.endDate.getText().toString();

        Log.e("filter", "acharid : " + acharid + "\nconsultation id : " + consultationId + "\nname : " + name
                + "\nstart date : " + startDate + "\nend date : " + endDate + "\nmonth : " + month);

        // String url = "https://theacharyamukti.com/managepanel/apis/earned.php?acharid=&consultation_id=&name=&month=&date_from=&date_to=";
        String url = "https://theacharyamukti.com/managepanel/apis/earned.php?acharid=" + acharid +
                "&consultation_id=" + consultationId +
                "&name=" + name +
                "&month=" + month.toLowerCase() +
                "&&date_from=" + startDate +
                "&&date_to=" + endDate;

        //String url = "https://theacharyamukti.com/managepanel/apis/earned.php?acharid=&consultation_id=&name=&month=&date_from=&date_to=%s";

        //String data = String.format(url,acharid, consultationId, name, month, startDate, endDate);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            binding.progressBarNew.setVisibility(View.INVISIBLE);
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
                    totalAmount += Float.parseFloat(earnData.getEaramount());
                    earningData.add(earnData);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.e("filter", earningData.toString());
            setData();

        }, error -> {
            binding.progressBarNew.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        });
        requestQueue.add(stringRequest);
    }

    private void setData() {
        binding.layoutTotal.setVisibility(View.VISIBLE);
        binding.total.setText(String.valueOf(totalAmount));

        binding.recyclerViewEarning.setVisibility(View.VISIBLE);
        binding.linearLayout.setVisibility(View.GONE);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerViewEarning.setLayoutManager(linearLayoutManager);
        binding.recyclerViewEarning.setHasFixedSize(true);
        EarningAdapter earningAdapter = new EarningAdapter(getApplicationContext(), earningData);
        earningAdapter.notifyDataSetChanged();
        binding.recyclerViewEarning.setAdapter(earningAdapter);
    }
}