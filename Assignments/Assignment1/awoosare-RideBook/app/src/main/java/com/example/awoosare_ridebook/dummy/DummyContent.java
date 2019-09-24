package com.example.awoosare_ridebook.dummy;

import com.example.awoosare_ridebook.Ride;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Ride> ITEMS = new ArrayList<Ride>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Ride> ITEM_MAP = new HashMap<String, Ride>();

    public static void addItem(Ride item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    //TODO temporary for filling in content
    static {
        // Add 3 sample items.
        addItem(new Ride(new Date(), 1, 2, 3, 4, "") );
        addItem(new Ride(new Date(), 5, 6, 7, 8, "ride 2's comment") );
        addItem(new Ride(new Date(), 88, 75, 4, 6, "ride 3's comment") );
    }
    //TODO temporary for filling in content

}
