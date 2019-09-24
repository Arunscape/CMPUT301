package com.example.awoosare_ridebook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;

import com.example.awoosare_ridebook.dummy.DummyContent;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.concurrent.atomic.AtomicBoolean;


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

    Ride ride;

    enum FormAction {
        CREATE, EDIT
    }

    FormAction formAction;

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


        DatePreview.setText(String.format("%04d-%02d-%02d", year, month, day));
        TimePreview.setText(String.format("%02d:%02d", hour, minute));

        String create_or_edit = getIntent().getStringExtra("type");
        Log.e("app", create_or_edit);
        if (create_or_edit.equals("CREATE_RIDE")) {
            this.ride = new Ride(calendar, Ride.getTimeToMinutes(hour, minute), 0, 0, 0, "");
            this.formAction = formAction.CREATE;
        } else if (create_or_edit.equals("EDIT_RIDE")) {
            String id = getIntent().getStringExtra("ride_id");
            this.ride = DummyContent.ITEM_MAP.get(id);
            this.formAction = FormAction.EDIT;
        }


        SetDateButton.setOnClickListener((View v) -> {
            DatePickerDialog.OnDateSetListener dateListener = (view, yyyy, mm, dd) -> {
                DatePreview.setText(String.format("%04d-%02d-%02d", yyyy, mm, dd));
                Calendar cal = Calendar.getInstance();
                cal.set(yyyy, mm, dd);
                this.ride.setDate(cal);
            };
            this.datePickerDialog = new DatePickerDialog(v.getContext(), dateListener, year, month, day);
            this.datePickerDialog.show();
        });

        SetTimeButton.setOnClickListener((View v) -> {
            TimePickerDialog.OnTimeSetListener timeListener = (view, hh, mm) ->
                    TimePreview.setText(String.format("%02d:%02d", hh, mm));
            this.timePickerDialog = new TimePickerDialog(v.getContext(), timeListener, hour, minute, true);
            this.timePickerDialog.show();
        });

        Distance.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!checkIfEditTextEmpty(Distance, hasFocus)) {
                this.setDistance();
            }

        });

        Speed.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!checkIfEditTextEmpty(Speed, hasFocus)) {
                this.setSpeed();
            }
        });

        RPM.setOnFocusChangeListener((View v, boolean hasFocus) -> {
            if (!checkIfEditTextEmpty(RPM, hasFocus)) {
                this.setRPM();
            }

        });

        fab.setOnClickListener((View v) -> {

            ArrayList<EditText> textBoxes = new ArrayList<>();
            textBoxes.add(Distance);
            textBoxes.add(Speed);
            textBoxes.add(RPM);

            AtomicBoolean error = new AtomicBoolean(false);

            textBoxes.forEach((EditText b) -> {
                checkIfEditTextEmpty(b, false);
                if (b.getError() != null) {
                    error.set(true);
                    return;
                }
            });

            if (error.get()) {
                Snackbar s = Snackbar.make(v, R.string.form_empty_field_message, Snackbar.LENGTH_SHORT);
                s.show();
                return;
            }

            this.setDistance();
            this.setSpeed();
            this.setRPM();
            this.setComment();

            if (this.formAction.equals(FormAction.CREATE)) {
                DummyContent.addItem(this.ride);
            }

            Intent intent = new Intent(this, ItemListActivity.class);
            startActivity(intent);

        });


    }

    private void setDistance() {
        this.ride.setDistance(Double.parseDouble(Distance.getText().toString()));
    }

    private void setSpeed() {
        this.ride.setAverageSpeed(Double.parseDouble(Speed.getText().toString()));
    }

    private void setRPM() {
        this.ride.setRpm(Integer.parseInt(RPM.getText().toString()));
    }

    private void setComment() {
        this.ride.setComment(Comment.getText().toString());
    }

    private boolean checkIfEditTextEmpty(EditText e, boolean hasFocus) {
        if (!hasFocus) {
            String s = e.getText().toString();
            if (s.equals("")) {
                e.setError("This field cannot be empty!");
                return true;
            }
            return false;
        }
        return true;
    }

}
