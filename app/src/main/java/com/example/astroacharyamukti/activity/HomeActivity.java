package com.example.astroacharyamukti.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.databinding.ActivityHomeActivityBinding;
import com.google.android.material.navigation.NavigationView;

import java.util.Calendar;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeActivityBinding binding;
    TextView schedule, date_picker, textScheduleDate, textScheduleTime;
    Toolbar toolbar;
    Button button_online;
    int date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeActivityBinding.inflate(getLayoutInflater());
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
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(this);

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
            case R.id.nav_performance:
                performanceDialog();
                break;
            case R.id.nav_help_support:
                Intent profileUpDate = new Intent(this, UserProfile.class);
                profileUpDate.putExtra("","1");
                startActivity(profileUpDate);
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
    }


    private void performanceDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.custom_performance_layout);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();

    }


    private void dialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_online_layout);
        Button button_online = dialog.findViewById(R.id.button_online);
        TextView schedule = dialog.findViewById(R.id.text_reschedule);
        textScheduleDate = dialog.findViewById(R.id.text_schedule_date);
        textScheduleTime = dialog.findViewById(R.id.time);
        button_online = dialog.findViewById(R.id.button_online);
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogSchedule();

            }
        });
        button_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
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
        TextView cancel = dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        text_Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (schedule.getText().toString().length() < 1 || date_picker.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "Please Select the date and Time", Toast.LENGTH_SHORT).show();
                } else {
                    dialog.dismiss();

                }

            }
        });
        date_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showHourPicker();
            }
        });
        schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });

        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    private void datePicker() {

        Calendar mCurrentDate = Calendar.getInstance();
        int year1 = mCurrentDate.get(Calendar.YEAR);
        int month1 = mCurrentDate.get(Calendar.MONTH);
        int day1 = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                date = Log.e("Date Selected", "Month: " + selectedMonth + " Day: " + selectedDay + " Year: " + selectedYear);
                schedule.setText((selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
                textScheduleDate.setText((selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
            }
        }, year1, month1, day1);
        mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    public void showHourPicker() {
        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);
                    date_picker.setText(hourOfDay + ":" + minute);
                    textScheduleTime.setText(hourOfDay + ":" + minute);
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        dialog();
//        if (button_online.getText().toString() == null) {
//            toolbar.setSubtitle("Online");
//        } else {
//            toolbar.setSubtitle("Offline");
//        }
    }
}