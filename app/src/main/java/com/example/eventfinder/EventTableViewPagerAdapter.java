package com.example.eventfinder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    ArrayList fragments;
    int tabsCount;

    public MainViewPagerAdapter(FragmentManager fm, Context context, int tabsCount) {
        super(fm);
        this.fragments = new ArrayList<>();
        this.fragments.add(new MainSearchFragment(context));
        this.fragments.add(new MainFavFragment(context));
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
