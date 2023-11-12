package com.example.eventfinder;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;


public class MainFavFragment extends Fragment {
    RecyclerView recyclerView;


    Context context;

    public MainFavFragment(Context context){
       this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fav_table, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.favRecyclerView);



        try {
            ArrayList eventNames = new ArrayList<>();
            ArrayList eventDates = new ArrayList<>();
            ArrayList eventTimes = new ArrayList<>();
            ArrayList eventVenues = new ArrayList<>();
            ArrayList eventCategories = new ArrayList<>();
            ArrayList eventIcons = new ArrayList<>();

            ArrayList eventIds = new ArrayList<>();

            SharedPreferences pref = context.getApplicationContext().getSharedPreferences("favourites", 0); // 0 - for private mode

            Map<String, ?> allEntries = pref.getAll();
            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
                String [] values = entry.getValue().toString().split(",");
                try {
                    eventNames.add(values[0]);
                    eventDates.add(values[1]);
                    eventTimes.add(values[2]);
                    eventVenues.add(values[3]);
                    eventCategories.add(values[4]);
                    eventIcons.add(values[5]);
                    eventIds.add(values[6]);

                } catch(Exception e){
                    System.out.println("Parse error in MainFavFragment");
                    e.printStackTrace();
                }
            }

            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);

            FavViewAdapter favViewAdapter = new FavViewAdapter(eventNames,eventIcons, eventDates, eventTimes, eventVenues, eventCategories, eventIds, context);
            recyclerView.setAdapter(favViewAdapter);
            System.out.println("Main Fav Done: recyclerView.setAdapter(favViewAdapter), and recyclerView.getAdapter() = " + recyclerView.getAdapter());
        } catch(Exception e){
            System.out.println("Error when dealing with onViewCreated in Main Fav Fragment");
            e.printStackTrace();
        }

        return view;
    }

}
