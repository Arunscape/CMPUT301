package com.example.awoosare_ridebook.dummy;
import com.example.awoosare_ridebook.Ride;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideData {

    public static final List<Ride> ITEMS = new ArrayList<Ride>();

    public static final Map<String, Ride> ITEM_MAP = new HashMap<String, Ride>();

    public static void addItem(Ride item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public static  void removeItem(Ride item){
        ITEMS.remove(item);
        ITEM_MAP.remove(item);
    }

}
