package com.example.awoosare_ridebook;

import java.util.ArrayList;
import java.util.HashMap;

// comments for the RideData class
// stores the global state for the application, (which is not persistent)
// the arrayList is for the recyclerview  in ItemListActivity, while the hashMap
// is there so that the ride details screen and the Ride Form activity
// to look up the ride. I probably could have just picked one, but
// it was convenient and felt more natural to use both, and there does not seem to be
// a performance hit

public class RideData {

    private static final ArrayList<Ride> ITEMS = new ArrayList<Ride>();
    private static final HashMap<String, Ride> ITEM_MAP = new HashMap<String, Ride>();

    public static ArrayList<Ride> getITEMS() {
        return ITEMS;
    }

    public static HashMap<String, Ride> getItemMap() {
        return ITEM_MAP;
    }

    public static void addItem(Ride item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public static void removeItem(Ride item) {
        ITEMS.remove(item);
        ITEM_MAP.remove(item);
    }

}
