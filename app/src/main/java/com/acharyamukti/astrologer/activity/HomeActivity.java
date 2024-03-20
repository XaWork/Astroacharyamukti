package com.acharyamukti.astrologer.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.acharyamukti.astrologer.helper.Backend;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.databinding.ActivityHomeActivityBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    TextView schedule, date_picker, textScheduleDate, textScheduleTime,
            name, email, fixedPrice, currentPrice;
    Toolbar toolbar;
    String status, newPrices;
    EditText newPrice;
    ProgressBar progressBar;
    Switch switchUse;
    Dialog dialog;
    String date = "";
    String time = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.astroacharyamukti.databinding.ActivityHomeActivityBinding binding = ActivityHomeActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarHomeActivity.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_actvity);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(this);
        name = findViewById(R.id.headerName);
        email = findViewById(R.id.header_email);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        dialog();
        getStatus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_actvity, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home_actvity);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_appointments:
                Intent intent = new Intent(getApplicationContext(), BookAppointment.class);
                startActivity(intent);
                break;
            case R.id.nav_language:
                Intent intent1 = new Intent(getApplicationContext(), LanguageActivity.class);
                startActivity(intent1);
                break;
            case R.id.nav_privacy_policy:
                Intent intent2 = new Intent(this, PrivacyPolicy.class);
                startActivity(intent2);
                break;
            case R.id.nav_my_earning:
                Intent earning = new Intent(this, MyEarningActivity.class);
                startActivity(earning);
                break;
            case R.id.nav_slideshow:
                Intent profile = new Intent(this, UserProfile.class);
                startActivity(profile);
                break;
            case R.id.nav_logout:
                Backend.getInstance(this).saveUserId("");
                Backend.getInstance(this).saveUserName("");
                Backend.getInstance(this).saveMobile("");
                Intent i5 = new Intent(getApplicationContext(), Login.class);
                i5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i5);
                this.finish();
                Toast.makeText(getApplicationContext(), "Logout", Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_help_support:
                Intent profileUpDate = new Intent(this, HelpAndSupport.class);
                profileUpDate.putExtra("", "1");
                startActivity(profileUpDate);
            case R.id.nav_FAQ:
                Intent faq = new Intent(this, FAQ.class);
                faq.putExtra("", "1");
                startActivity(faq);
                break;
            case R.id.nav_term_condition:
                String termConditionUrl = "https://theacharyamukti.com/terms-and-conditions.php";
                Intent terms = new Intent(Intent.ACTION_VIEW);
                terms.putExtra("", "1");
                terms.setData(Uri.parse(termConditionUrl));
                startActivity(terms);
                break;
            case R.id.person:
                Intent intent3 = new Intent(getApplicationContext(), BookAppointment.class);
                intent3.putExtra("", "1");
                startActivity(intent3);
                break;
            case R.id.homeEarning:
                Intent homeEarning = new Intent(getApplicationContext(), MyEarningActivity.class);
                homeEarning.putExtra("", "3");
                startActivity(homeEarning);
                break;
            case R.id.settings:
                Intent customerReview = new Intent(getApplicationContext(), ReviewActivity.class);
                customerReview.putExtra("", "4");
                startActivity(customerReview);
                break;
            case R.id.nav_call_price:
                callPrice();
                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }


    private void callPrice() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_performance_layout);
        Button updatePrice = dialog.findViewById(R.id.updatePrice);
        fixedPrice = dialog.findViewById(R.id.fixedPrice);
        currentPrice = dialog.findViewById(R.id.currentPrice);
        newPrice = dialog.findViewById(R.id.postNewPrice);
        newPrices = newPrice.getText().toString();
        callPriceUpDate();
        Backend.getInstance(this).saveNewPrice(newPrices);
        updatePrice.setOnClickListener(view -> {
            Toast.makeText(getApplicationContext(), "New Price Update", Toast.LENGTH_SHORT).show();
            callPriceUpDate();
            dialog.dismiss();
        });
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }


    private void dialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_online_layout);
        TextView schedule = dialog.findViewById(R.id.text_reschedule);
        textScheduleDate = dialog.findViewById(R.id.text_schedule_date);
        textScheduleTime = dialog.findViewById(R.id.time);
        switchUse = dialog.findViewById(R.id.switchCase);
        String status = Backend.getInstance(this).getUserStatus();
        switchUse.setOnCheckedChangeListener((buttonView, isChecked) -> updateStatus(isChecked));
        schedule.setOnClickListener(view -> dialogSchedule());
        ImageView cancel = dialog.findViewById(R.id.cancel_image);
        cancel.setOnClickListener(view -> dialog.cancel());
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void dialogSchedule() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_schedule);
        dialog.setCanceledOnTouchOutside(true);
        schedule = dialog.findViewById(R.id.text_calender);
        date_picker = dialog.findViewById(R.id.date_picker);
        TextView text_Confirm = dialog.findViewById(R.id.text_Confirm);
        TextView cancel = dialog.findViewById(R.id.cancel_schedule);
        cancel.setOnClickListener(view -> dialog.dismiss());
        text_Confirm.setOnClickListener(view -> {
            if (schedule.getText().toString().length() < 1 || date_picker.getText().toString().length() < 1) {
                Toast.makeText(getApplicationContext(), "Please Select the date and Time", Toast.LENGTH_SHORT).show();
            } else {
                updateStatus(false);
                dialog.dismiss();

            }

        });
        cancel.setOnClickListener(view -> dialog.cancel());
        date_picker.setOnClickListener(view -> showHourPicker());
        schedule.setOnClickListener(view -> datePicker());
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void datePicker() {
        Calendar mCurrentDate = Calendar.getInstance();
        int year1 = mCurrentDate.get(Calendar.YEAR);
        int month1 = mCurrentDate.get(Calendar.MONTH);
        int day1 = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        @SuppressLint("SetTextI18n") DatePickerDialog mDatePicker = new DatePickerDialog(this, (datepicker, selectedYear, selectedMonth, selectedDay) -> {
            date = selectedDay + "/" + selectedMonth+1 + "/" + selectedYear;
            Log.e("Date Selected", "Month: " + selectedMonth+1 + " Day: " + selectedDay + " Year: " + selectedYear);
            schedule.setText((selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
            textScheduleDate.setText((selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
        }, year1, month1, day1);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    public void showHourPicker() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);

        @SuppressLint("SetTextI18n") TimePickerDialog.OnTimeSetListener myTimeListener = (view, hourOfDay, minute1) -> {
            if (view.isShown()) {
                myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalender.set(Calendar.MINUTE, minute1);
                date_picker.setText(hourOfDay + ":" + minute1);
                textScheduleTime.setText(hourOfDay + ":" + minute1);
                time = hourOfDay + ":" + minute1;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                dialog();
                getStatus();
                break;
            case R.id.imageViewHeader:
                Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                startActivity(intent);
                break;
        }
    }

    private void getStatus() {
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/get-status.php";
        String dataUrl = String.format(url, userId);
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dataUrl, response -> {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                status = jsonObject.getString("status");
                if (status.equals("Online")) {
                    toolbar.setSubtitle(status);
                    switchUse.setChecked(true);
                } else {
                    toolbar.setSubtitle(status);
                    switchUse.setChecked(false);
                    dialog();
                }
                Backend.getInstance(getApplicationContext()).saveUserStatus(status);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

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

    private void updateStatus(boolean checked) {
        String status = checked ? "Online" : "Offline";
        toolbar.setSubtitle(status);
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/update-status.php";
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status1 = jsonObject.getString("status");
                String msg = jsonObject.getString("msg");
                if (status1.equals("true")) {
                      Toast.makeText(HomeActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(HomeActivity.this, status, Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                params.put("new_status", status);
                params.put("schedule_date", date);
                params.put("schedule_time", time);
                return params;
            }
        };
        request.add(stringRequest);
    }

    private void callPriceUpDate() {
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/rate-up.php";
        String dataUrl = String.format(url, userId);
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dataUrl, response -> {
            progressBar.setVisibility(View.INVISIBLE);
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                String fixedPri = jsonObject.getString("fix_price");
                String currentPri = jsonObject.getString("current_price");
                if (status.equals("true")) {
                    fixedPrice.setText(fixedPri);
                    currentPrice.setText(currentPri);
                    progressBar.setVisibility(View.INVISIBLE);
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }, error -> {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(HomeActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("acharid", userId);
                params.put("current", newPrices = newPrice.getText().toString());
                return params;
            }
        };
        request.add(stringRequest);
    }

    @Override
    protected void onDestroy() {
        dialog.dismiss();
        super.onDestroy();
    }
}