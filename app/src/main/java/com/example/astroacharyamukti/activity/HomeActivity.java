package com.example.astroacharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.helper.Backend;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.example.astroacharyamukti.databinding.ActivityHomeActivityBinding;

import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    TextView schedule, date_picker, textScheduleDate, textScheduleTime,
            name, email;
    Toolbar toolbar;
    Button button_online;
    String status;
    int date;

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
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }


//    private void performanceDialog() {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCancelable(false);
//        dialog.setContentView(R.layout.custom_performance_layout);
//        dialog.setCanceledOnTouchOutside(true);
//        dialog.show();
//
//    }


    private void dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_online_layout);
        button_online = dialog.findViewById(R.id.button_online);
        TextView schedule = dialog.findViewById(R.id.text_reschedule);
        textScheduleDate = dialog.findViewById(R.id.text_schedule_date);
        textScheduleTime = dialog.findViewById(R.id.time);
        button_online = dialog.findViewById(R.id.button_online);
        schedule.setOnClickListener(view -> dialogSchedule());
        button_online.setOnClickListener(view -> {
            String getStatus = Backend.getInstance(getApplicationContext()).getUserStatus();
            if (getStatus.equals("Online")) {
                dialog.cancel();
            } else {
                Toast.makeText(getApplicationContext(), "Astrologer is offline schedule call for some time", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView cancel = dialog.findViewById(R.id.cancel_image);
        cancel.setOnClickListener(view -> dialog.cancel());
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void dialogSchedule() {
        Dialog dialog = new Dialog(this);
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
                //  updateStatus();
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
            date = Log.e("Date Selected", "Month: " + selectedMonth + " Day: " + selectedDay + " Year: " + selectedYear);
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
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.......Please wait");
        progressDialog.show();
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dataUrl, response -> {
            progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                status = jsonObject.getString("status");
                if (status.equals("Online")) {
                    toolbar.setSubtitle(status);
                } else {
                    toolbar.setSubtitle(status);
                    dialog();
                }
                Backend.getInstance(getApplicationContext()).saveUserStatus(status);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();

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

    private void updateStatus() {
        String userId = Backend.getInstance(this).getUserId();
        String url = "https://theacharyamukti.com/managepanel/apis/update-status.php";
        String dataUrl = String.format(url, userId);
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.......Please wait");
        progressDialog.show();
        RequestQueue request = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, dataUrl, response -> {
            progressDialog.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(response);
                String status = jsonObject.getString("status");
                if (status.equals("true")) {
                    finish();
                } else {
                    Toast.makeText(HomeActivity.this, status, Toast.LENGTH_SHORT).show();

                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            progressDialog.dismiss();
            Toast.makeText(HomeActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }) {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("new_status", status);
                params.put("schedule_date", schedule.getText().toString());
                params.put("schedule_time", date_picker.getText().toString());
                return params;
            }
        };
        request.add(stringRequest);
    }
}