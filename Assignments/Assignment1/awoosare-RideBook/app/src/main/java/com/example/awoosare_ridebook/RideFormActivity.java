package com.example.awoosare_ridebook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.MessageFormat;
import java.util.Locale;


public class RideFormActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView DatePreview;
    Button SetDateButton;
    TextView TimePreview;
    Button SetTimeButton;
    EditText Distance;
    EditText Speed;
    EditText RPM;
    EditText Comment;

    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = findViewById(R.id.fab);
        this.DatePreview = findViewById(R.id.FormDatePreview);
        this.SetDateButton = findViewById(R.id.FormDateButton);
        this.TimePreview = findViewById(R.id.FormTimePreview);
        this.SetTimeButton = findViewById(R.id.FormTimeButton);
        this.Distance = findViewById(R.id.FormDistance);
        this.Speed = findViewById(R.id.FormSpeed);
        this.RPM = findViewById(R.id.FormRPM);
        this.Comment = findViewById(R.id.FormComment);

        final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // WHYYYYYYYYYYYYYY
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        DatePreview.setText(String.format(Locale.CANADA, "%04d-%02d-%02d", year, month, day));
        TimePreview.setText(String.format("%02d:%02d", hour, minute));


        DatePickerDialog.OnDateSetListener dateListener = (view, yyyy, mm, dd) ->
                DatePreview.setText(String.format("%04d-%02d-%02d", yyyy, mm, dd));

        TimePickerDialog.OnTimeSetListener timeListener = (view, hh, mm) ->
                TimePreview.setText(String.format("%02d:%02d", hh, mm));


        SetDateButton.setOnClickListener((View v) -> {
            this.datePickerDialog = new DatePickerDialog(v.getContext(), dateListener, year, month, day);
            this.datePickerDialog.show();
        });

        SetTimeButton.setOnClickListener((View v) -> {
            this.timePickerDialog = new TimePickerDialog(v.getContext(), timeListener, hour, minute, true);
            this.timePickerDialog.show();
        });


        Distance.setOnFocusChangeListener((view, hasFocus) -> {
            checkIfEditTextEmpty(Distance, hasFocus);
        });

        Speed.setOnFocusChangeListener((view, hasFocus) -> {
            checkIfEditTextEmpty(Speed, hasFocus);
        });

        RPM.setOnFocusChangeListener((view, hasFocus) -> {
            checkIfEditTextEmpty(RPM, hasFocus);
        });

        Comment.setOnFocusChangeListener((view, hasFocus) -> {

        });


    }

    private void checkIfEditTextEmpty(EditText e, boolean hasFocus) {
        if (!hasFocus) {
            String s = e.getText().toString();
            if (s.equals("")) {
                e.setError("This field cannot be empty!");
            }
        }
    }

}
