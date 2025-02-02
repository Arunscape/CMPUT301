package com.example.awoosare_ridebook;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

// comments for the RideFormActivity class
// handles the creation of a new ride, and editing an existing ride
// it knows whether to add a new ride or edit one based on the intent that is passed to it
// type: CREATE_RIDE | EDIT RIDE
// if the type is EDIT_RIDE, then the ride id is passed as another intent with key ride_id
// the form is then pre-populated with the same data that existed beforehand, and the user
// can edit the ride's info, or leave the fields as they wish
// otherwise, if CREATE_RIDE was passed as an intent, the time and date are automatically
// set to the current date and time. The user is then required to fill out the Distance, Time,
// or Cadence fields. (The user can still change the date and time or add a comment if desired)
// the Distance and Speed fields are restricted to decimal inputs, and the
// Cadence field is restricted to integers. Furthermore, the user will be prompted
// to fill in these fields if they are left empty. (The comment field may still be left blank)
// Additionally, the user can delete the ride from this screen.

public class RideFormActivity extends AppCompatActivity {

    private FloatingActionButton fabConfirm;
    private FloatingActionButton fabDelete;
    private TextView DatePreview;
    private Button SetDateButton;
    private TextView TimePreview;
    private Button SetTimeButton;
    private EditText Distance;
    private EditText Speed;
    private EditText RPM;
    private EditText Comment;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Ride ride;

    private enum FormAction {
        CREATE, EDIT
    }

    private FormAction formAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.fabConfirm = findViewById(R.id.fab);
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
            this.formAction = FormAction.CREATE;
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
            this.ride = RideData.getItemMap().get(id);

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

        fabConfirm.setOnClickListener((View v) -> {

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
        this.ride.setSpeed(Double.parseDouble(Speed.getText().toString()));
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
