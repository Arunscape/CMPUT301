package com.example.awoosare_ridebook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;

import com.example.awoosare_ridebook.dummy.RideData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import java.util.concurrent.atomic.AtomicBoolean;


public class RideFormActivity extends AppCompatActivity {

    FloatingActionButton fab;
    FloatingActionButton fabDelete;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.fab = findViewById(R.id.fab);
        this.fabDelete = findViewById(R.id.fabDelete);
        this.DatePreview = findViewById(R.id.FormDatePreview);
        this.SetDateButton = findViewById(R.id.FormDateButton);
        this.TimePreview = findViewById(R.id.FormTimePreview);
        this.SetTimeButton = findViewById(R.id.FormTimeButton);
        this.Distance = findViewById(R.id.FormDistance);
        this.Speed = findViewById(R.id.FormSpeed);
        this.RPM = findViewById(R.id.FormRPM);
        this.Comment = findViewById(R.id.FormComment);

        final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        String create_or_edit = getIntent().getStringExtra("type");

        if (create_or_edit.equals("CREATE_RIDE")) {
            this.formAction = formAction.CREATE;
            this.ride = new Ride(
                    calendar,
                    0,
                    0,
                    0,
                    ""
            );

        } else if (create_or_edit.equals("EDIT_RIDE")) {
            this.formAction = FormAction.EDIT;
            String id = getIntent().getStringExtra("ride_id");
            this.ride = RideData.ITEM_MAP.get(id);

            Distance.setText(this.ride.getDistance(true));
            Speed.setText(this.ride.getSpeed(true));
            RPM.setText(this.ride.getRPM(true));
            Comment.setText(this.ride.getComment());

        }

        DatePreview.setText(this.ride.getDate());
        TimePreview.setText(this.ride.getTime());

        SetDateButton.setOnClickListener((View v) -> {
            Calendar cal = Calendar.getInstance();
            DatePickerDialog.OnDateSetListener dateListener = (view, yyyy, mm, dd) -> {
                cal.set(yyyy, mm, dd);
                this.ride.setDate(cal);
                DatePreview.setText(this.ride.getDate());
            };
            this.datePickerDialog = new DatePickerDialog(
                    v.getContext(),
                    dateListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
            );
            this.datePickerDialog.show();
        });


        SetTimeButton.setOnClickListener((View v) -> {
            TimePickerDialog.OnTimeSetListener timeListener = (view, hh, mm) -> {
                this.ride.setTime(hh, mm);
                TimePreview.setText(this.ride.getTime());
            };
            this.timePickerDialog = new TimePickerDialog(
                    v.getContext(),
                    timeListener,
                    this.ride.getTimeHourOfDay(),
                    this.ride.getTimeMinuteOfHour(),
                    true
            );
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
                Snackbar s = Snackbar.make(
                        v,
                        R.string.form_empty_field_message,
                        Snackbar.LENGTH_SHORT
                );
                s.show();
                return;
            }

            this.setDistance();
            this.setSpeed();
            this.setRPM();
            this.setComment();

            if (this.formAction.equals(FormAction.CREATE)) {
                RideData.addItem(this.ride);
            }

            Intent intent = new Intent(this, ItemListActivity.class);
            startActivity(intent);

        });

        fabDelete.setOnClickListener((View v) -> {
            if (this.formAction.equals(FormAction.EDIT)) {
                RideData.removeItem(this.ride);
            } else if (this.formAction.equals(FormAction.CREATE)) {
                Ride.decrementId(); // this id can be re-used later
            }
            Intent intent = new Intent(this, ItemListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (this.formAction.equals(FormAction.CREATE)) {
            Ride.decrementId(); // we can reuse the id if back the button is pressed
        }
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        onSupportNavigateUp(); // we can reuse the id if back the button is pressed
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
