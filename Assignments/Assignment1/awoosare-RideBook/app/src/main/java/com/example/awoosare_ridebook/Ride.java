package com.example.awoosare_ridebook;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.icu.util.Calendar;

public class Ride {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private Calendar date;
    private long time; // minutes
    private double distance; // kilometres
    private double average_speed; // km/h
    private long rpm;
    private String comment; // limited to 20 characters

    private static long nextId = 1;
    private long id;


    public Ride(Calendar date, long time, double distance, double average_speed, long rpm, String comment) throws InvalidRideException {
        if (time < 0 || distance < 0 || average_speed < 0 || rpm < 0 || comment.length() > 20) {
            throw new InvalidRideException("This ride is not valid. Make sure time, distance, average speed, and rpm are all POSITIVE, and that the comment LENGTH is at most 20");
        }
        this.date = date;
        this.time = time;
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

    public Calendar getDateAsCalendar(){
        return this.date;
    }

    public static String getDate(Calendar date) {
        return dateFormat.format(date.getTime());
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getTime() {
        return String.format("%02d:%02d", TimeUnit.MINUTES.toHours(this.time), this.time % 60);
    }

    public int getTimeHourOfDay(){
        return (int) TimeUnit.MINUTES.toHours(this.time);
    }

    public int getTimeMinuteOfHour(){
        return (int) time % 60;
    }

    public static String getTime(long time) {
        return String.format("%02d:%02d", TimeUnit.MINUTES.toHours(time), time % 60);
    }

    public static String getTime(int hours, int minutes) {
        return String.format("%02d:%02d", hours, minutes);
    }

    public static long getTimeToMinutes(int hours, int minutes) {
        return TimeUnit.HOURS.toMinutes(hours) + minutes;
    }

    public void setTime(long time) {
        if (time > 0) {
            this.time = time;
        }
    }

    public void setTime(int hours, int minutes) {
        this.setTime(getTimeToMinutes(hours, minutes));
    }

    public String getDistance() {
        return String.format("%f km", this.distance);
    }

    public String getDistance(boolean raw){
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

    public String getSpeed(boolean raw){
        return raw ? Double.toString(this.average_speed) : getSpeed();
    }

    public void setAverageSpeed(double average_speed) {
        if (average_speed > 0) {
            this.average_speed = average_speed;
        }
    }

    public String getRPM() {
        return String.format("%d rev/min", this.rpm);
    }

    public String getRPM(boolean raw){
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
        // TODO IDK
//        if (comment.length() > 20) {
//            this.comment = comment.substring(0, 20);
//        } else {
//            this.comment = comment;
//        }
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format(
                "Date: %s\n Time: %s\n Distance(km): %s\n Avg Speed(km/h): %s\n RPM: %s\n Comment: %s\n",
                this.getDate(), this.getTime(), this.getDistance(), this.getSpeed(), this.getRPM(), this.getComment());
    }

    public String getId() {
        return String.valueOf(this.id);
    }

}

class InvalidRideException extends IllegalArgumentException {
    public InvalidRideException(String message) {
        super(message);
    }
}