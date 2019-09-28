package com.example.awoosare_ridebook;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Ride mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = RideData.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);

            if (appBarLayout != null) {
                appBarLayout.setTitle(String.format("Ride %s on %s", mItem.getId(), mItem.getDate()));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ride_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.rideDetailDateContent)).setText(mItem.getDate());
            ((TextView) rootView.findViewById(R.id.rideDetailTimeContent)).setText(mItem.getTime());
            ((TextView) rootView.findViewById(R.id.rideDetailDistanceContent)).setText(mItem.getDistance());
            ((TextView) rootView.findViewById(R.id.rideDetailSpeedContent)).setText(mItem.getSpeed());
            ((TextView) rootView.findViewById(R.id.rideDetailRPMContent)).setText(mItem.getRPM());
            ((TextView) rootView.findViewById(R.id.rideDetailCommentContent)).setText(mItem.getComment());

        }

        return rootView;
    }
}
