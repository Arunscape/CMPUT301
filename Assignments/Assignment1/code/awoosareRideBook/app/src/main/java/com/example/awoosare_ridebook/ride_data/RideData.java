package com.example.awoosare_ridebook.ride_data;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Helper class for providing
 */
public class RideData {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Ride> ITEMS = new ArrayList<Ride>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Ride> ITEM_MAP = new HashMap<String, Ride>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Ride item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static Ride createDummyItem(int position) {
        return new Ride(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    public static class Ride {

        private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        private Date date;
        private long time; // minutes
        private double distance; // kilometres
        private double average_speed; // km/h
        private long rpm;
        private String comment; // limited to 20 characters

        public Ride(Date date, long time, double distance, double average_speed, long rpm, String comment) throws InvalidRideException{
            if (time < 0 || distance < 0 || average_speed < 0 || rpm < 0 || comment.length() > 20){
                throw new InvalidRideException("This ride is not valid. Make sure time, distance, average speed, and rpm are all POSITIVE, and that the comment LENGTH is at most 20");
            }
            this.date = date;
            this.time = time;
            this.distance = distance;
            this.average_speed = average_speed;
            this.rpm = rpm;
            this.comment = comment;
        }

        public String getDate(){
            return dateFormat.format((this.date));
        }
        public void setDate(Date date){
            this.date = date;
        }

        public String getTime(){
            return String.format("%02d:%02d", TimeUnit.MINUTES.toHours(this.time), this.time % 60)
        }
        public void setTime(long time){
            if (time > 0){
                this.time = time;
            }
        }

        public String getDistance(){
            return String.format("%f km", this.distance);
        }
        public void setDistance(double distance){
            if (distance > 0){
                this.time = time;
            }
        }

        public String getAverageSpeed(){
            return String.format("%f km/h", this.average_speed);
        }
        public void setAverageSpeed(double average_speed){
            if (average_speed > 0){
                this.average_speed = average_speed;
            }
        }

        public String getRPM(){
            return String.format("%d rev/min", this.rpm);
        }
        public void setRpm(long rpm){
            if (rpm > 0){
                this.rpm = rpm;
            }
        }

        public String getComment(){
            return this.comment;
        }
        public String setComment(String comment){
            this.comment = comment.substring(0,20);
        }

        @Override
        public String toString(){
            return String.format(
                    "Date: %s\n Time: %s\n Distance(km): %s\n Avg Speed(km/h): %s\n RPM: %s\n Comment: %s\n",
                    this.getDate(), this.getTime(), this.getDistance(), this.getAverageSpeed(), this.getRPM(), this.getComment())
        }
    }
}

class InvalidRideException extends  Exception{
    public InvalidRideException(String message){
        super(message);
    }
}
