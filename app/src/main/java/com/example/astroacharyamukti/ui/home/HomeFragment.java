package com.example.astroacharyamukti.ui.home;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.astroacharyamukti.R;
import com.example.astroacharyamukti.databinding.FragmentHomeBinding;

import org.w3c.dom.Text;

import java.util.Calendar;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private FragmentHomeBinding binding;
    TextView schedule, date_picker,textScheduleDate,textScheduleTime;
    String date,time;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        Button btn_submit = root.findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void dialog() {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_online_layout);
        Button button_online = dialog.findViewById(R.id.button_online);
        TextView schedule = dialog.findViewById(R.id.text_reschedule);
        textScheduleDate=dialog.findViewById(R.id.text_schedule_date);
        textScheduleTime=dialog.findViewById(R.id.text_schedule_time);

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
        Dialog dialog = new Dialog(getContext());
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
                    Toast.makeText(getContext(), "Please Select the date and Time", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        dialog();
    }

    private void datePicker() {

        Calendar mCurrentDate = Calendar.getInstance();
        int year1 = mCurrentDate.get(Calendar.YEAR);
        int month1 = mCurrentDate.get(Calendar.MONTH);
        int day1 = mCurrentDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                Log.e("Date Selected", "Month: " + selectedMonth + " Day: " + selectedDay + " Year: " + selectedYear);
                schedule.setText((selectedMonth + 1) + "/" + selectedDay + "/" + selectedYear);
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
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_NoActionBar, myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose hour:");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }
}

