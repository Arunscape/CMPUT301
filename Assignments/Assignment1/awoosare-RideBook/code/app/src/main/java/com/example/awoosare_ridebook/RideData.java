package com.example.awoosare_ridebook;
import com.example.awoosare_ridebook.Ride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideData {

    private static final ArrayList<Ride> ITEMS = new ArrayList<Ride>();
    private static final Map<String, Ride> ITEM_MAP = new HashMap<String, Ride>();

    public static ArrayList<Ride> getITEMS() {
        return ITEMS;
    }

    public static Map<String, Ride> getItemMap() {
        return ITEM_MAP;
    }


    public static void addItem(Ride item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public static void removeItem(Ride item){
        ITEMS.remove(item);
        ITEM_MAP.remove(item);
    }

}
