package com.example.eventfinder;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;

import com.android.volley.Response;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EventDetailsViewPagerAdapter extends FragmentPagerAdapter {

    JSONObject eventDetails;
    ArrayList artistsDetails;
    JSONObject venueDetails;
    ArrayList artistNames;
    ArrayList fragments;
    int tabsCount;

    public EventDetailsViewPagerAdapter(FragmentManager fm, Lifecycle lifecycle, JSONObject eventDetails, ArrayList artistsDetails, JSONObject venueDetails, ArrayList artistNames, Response.Listener<String> listener, int tabsCount) {
        super(fm);
        this.eventDetails = eventDetails;
        this.artistsDetails = artistsDetails;
        this.venueDetails = venueDetails;
        this.artistNames = artistNames;
        this.fragments = new ArrayList<>();
        this.fragments.add(new EDFragment(eventDetails, artistNames));

        this.fragments.add(new EVFragment(venueDetails));
        this.fragments.add(new EAFragment(artistsDetails));
        this.tabsCount = tabsCount;


//        try{
//            SupportMapFragment mapFragment = (SupportMapFragment) fm.findFragmentById(R.id.googleMap);
//
//            if (mapFragment != null) {
//                System.out.println("Load map now in EventDetailsViewPagerAdapter");
//                mapFragment.getMapAsync((OnMapReadyCallback) listener);
//            }
//        } catch (Exception e) {
//            System.out.println("Error when dealing with Google Map mapFragment");
//            e.printStackTrace();
//        }

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        System.out.println("position = " + position);

        return (Fragment) fragments.get(position);
    }

    @Override
    public int getCount() {
//        System.out.println("tabsCount = " + tabsCount);
        return tabsCount;
    }
}
