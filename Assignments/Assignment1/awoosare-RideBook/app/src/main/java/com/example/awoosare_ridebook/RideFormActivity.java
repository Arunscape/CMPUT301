package com.example.awoosare_ridebook;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Time;

public class RideFormActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView DatePreview;
    TextView TimePreview;
//    TimePicker Time;
    Button SetTimeButton;
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
        this.TimePreview = findViewById(R.id.FormTimePreview);
//        this.TimeHours = findViewById(R.id.FormTimeHours);
//        this.TimeMinutes = findViewById(R.id.FormTimeMinutes);
        this.SetTimeButton = findViewById(R.id.FormTimeButton);
        this.Distance = findViewById(R.id.FormDistance);
        this.Speed = findViewById(R.id.FormSpeed);
        this.RPM = findViewById(R.id.FormRPM);
        this.Comment = findViewById(R.id.FormComment);


        Distance.setOnFocusChangeListener((view, hasFocus) -> {
            checkIfEditTextEmpty(Distance, hasFocus);
        });

        Speed.setOnFocusChangeListener((view, hasFocus) -> {
            checkIfEditTextEmpty(Speed, hasFocus);
        });

        RPM.setOnFocusChangeListener((view, hasFocus) -> {
            checkIfEditTextEmpty(RPM, hasFocus);
        });


    }

    private void checkIfEditTextEmpty (EditText e, boolean hasFocus){
        if (!hasFocus){
            String s = e.getText().toString();
            if( s.equals("")){
                e.setError("This field cannot be empty!");
            }
        }
    }

}
