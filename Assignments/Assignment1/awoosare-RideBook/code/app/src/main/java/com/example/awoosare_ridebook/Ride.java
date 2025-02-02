package com.example.awoosare_ridebook;

import android.icu.util.Calendar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

// comments for the Ride class
// This class contains all the details needed to display a ride in the list, as well as for viewing
// the ride's details. It also has some helper functions for displaying the date and the time
// in the correct format. Data validation is done, even though the UI already only accepts valid
// input, however, it doesn't hurt to check twice unless it significantly hinders performance
// (which does not seem to be a problem)
// Additionally, there is a static variable that assigns each ride a unique id, so that
// when a ride is clicked in the Item List Activity we know which ride details to pull up

public class Ride {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateFormat timeFormat = new SimpleDateFormat("HH:mm");

    private Calendar date;
    private double distance; // kilometres
    private double average_speed; // km/h
    private long rpm;
    private String comment; // limited to 20 characters

    private static long nextId = 1;
    private long id;


    public Ride(Calendar date, double distance, double average_speed, long rpm, String comment) throws InvalidRideException {
        if (distance < 0 || average_speed < 0 || rpm < 0 || comment.length() > 20) {
            throw new InvalidRideException("This ride is not valid. Make sure time, distance, average speed, and rpm are all POSITIVE, and that the comment LENGTH is at most 20");
        }
        this.date = date;
        this.distance = distance;
        this.average_speed = average_speed;
        this.rpm = rpm;
        this.comment = comment;
        this.id = nextId;
        nextId += 1;
    }

    public String getDate() {
        return dateFormat.format((this.date.getTime()));
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTime() {
        return timeFormat.format(date.getTime());
    }

    public int getTimeHourOfDay() {
        return this.date.get(Calendar.HOUR_OF_DAY);
    }

    public int getTimeMinuteOfHour() {
        return this.date.get(Calendar.MINUTE);
    }

    public void setTime(int hour, int minute) {
        this.date.set(Calendar.HOUR_OF_DAY, hour);
        this.date.set(Calendar.MINUTE, minute);
    }

    public String getDistance() {
        return String.format("%f km", this.distance);
    }

    public String getDistance(boolean raw) {
        return raw ? Double.toString(this.distance) : getDistance();
    }

    public void setDistance(double distance) {
        if (distance > 0) {
            this.distance = distance;
        }
    }

    public String getSpeed() {
        return String.format("%f km/h", this.average_speed);
    }

    public String getSpeed(boolean raw) {
        return raw ? Double.toString(this.average_speed) : getSpeed();
    }

    public void setSpeed(double average_speed) {
        if (average_speed > 0) {
            this.average_speed = average_speed;
        }
    }

    public String getRPM() {
        return String.format("%d rev/min", this.rpm);
    }

    public String getRPM(boolean raw) {
        return raw ? Long.toString(this.rpm) : getRPM();
    }

    public void setRpm(long rpm) {
        if (rpm > 0) {
            this.rpm = rpm;
        }
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        if (comment.length() > 20) {
            // already limited by the EditText box but it doesn't hurt to check again
            this.comment = comment.substring(0, 20);
        } else {
            this.comment = comment;
        }
    }

    public String getId() {
        return String.valueOf(this.id);
    }

    public static void decrementId() {
        nextId -= 1;
    }

}

class InvalidRideException extends IllegalArgumentException {
    public InvalidRideException(String message) {
        super(message);
    }
}