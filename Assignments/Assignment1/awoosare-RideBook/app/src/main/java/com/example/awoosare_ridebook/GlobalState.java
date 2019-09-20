package com.example.awoosare_ridebook;

import java.util.ArrayList;
import java.util.Date;

public class GlobalState {

    private static GlobalState singleton = null;

    ArrayList<Ride> rides;

    GlobalState() {
            Ride Ride0 = new Ride(new Date(), 0, 0, 0, 0, null);
            Ride Ride1 = new Ride(new Date(), 1, 1, 1, 1, "This is a comment");
            Ride Ride2 = new Ride(new Date(), 2, 2, 2, 2, "This is another comment");

            rides.add(Ride0);
            rides.add(Ride1);
            rides.add(Ride2);

            //TODO this will be just new Arraylist<>() when this portion is complete


    }

    public static GlobalState getState(){
        if (singleton == null) {
            singleton = new GlobalState();
        }
        return singleton;
    }


    public ArrayList<Ride> getRides() {
        return this.rides;
    }

    public void addRide(Ride ride){
        this.rides.add(ride);
    }

    public void editRide(int position, Ride ride){
        this.rides.set(position, ride);
    }


}
