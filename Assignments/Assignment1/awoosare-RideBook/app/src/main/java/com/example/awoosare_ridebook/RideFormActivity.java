package com.example.awoosare_ridebook;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Time;

public class RideFormActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView DatePreview;
    EditText TimeHours;
    EditText TimeMinutes;
    EditText Distance;
    EditText Speed;
    EditText RPM;
    EditText Comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.fab = findViewById(R.id.fab);
        this.DatePreview = findViewById(R.id.FormDatePreview);
        this.TimeHours = findViewById(R.id.FormTimeHours);
        this.TimeMinutes = findViewById(R.id.FormTimeMinutes);
        this.Distance = findViewById(R.id.FormDistance);
        this.Speed = findViewById(R.id.FormSpeed);
        this.RPM = findViewById(R.id.FormRPM);
        this.Comment = findViewById(R.id.FormComment);


        TimeHours.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                String hour = TimeHours.getText().toString();
                if (hour.equals("")) {
                    TimeHours.setError("This field cannot be empty!");
                } else if (!hour.equals("") && Integer.parseInt(hour) > 24) {
                    TimeHours.setError("Hour must be between 00-24 inclusive");
                }
            }
        });

        TimeMinutes.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                String minute = TimeMinutes.getText().toString();
                if (minute.equals("")) {
                    TimeMinutes.setError("This field cannot be empty!");
                } else if (!minute.equals("") && Integer.parseInt(minute) > 59) {
                    TimeMinutes.setError("Minute must be between 00-59 inclusive");
                }
            }
        });

        Distance.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus){
                String distance = Distance.getText().toString();
                if(distance.equals("")){
                    Distance.setError("This field cannot be empty!");
                }
            }
        });
    }

}
