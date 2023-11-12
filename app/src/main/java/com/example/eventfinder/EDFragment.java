package com.example.eventfinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;

public class EDSection extends Section {
    RecyclerView recyclerView;
    JSONObject eventDetails;
    ArrayList artistNames;

    public EDSection(JSONObject eventDetails, ArrayList artistNames){
        this.eventDetails = eventDetails;
        this.artistNames = artistNames;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(
                R.layout.event_details, container, false);
        return rootView;
    }

    public EDSection.EDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_details_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EDSection.EDViewHolder vh = new EDSection.EDViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.edRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        try {
            ArrayList venues = new ArrayList<>(Arrays.asList(eventDetails.getString("venueName")));
            String [] dt = eventDetails.getString("dt").split(" ");

            ArrayList dates =  new ArrayList<>(Arrays.asList(dt[0]));
            ArrayList times = new ArrayList<>(dt.length > 0? Arrays.asList(dt[1]): Arrays.asList(""));
            ArrayList genres = new ArrayList<>(Arrays.asList(eventDetails.getString("genres")));
            ArrayList priceRanges = new ArrayList<>(Arrays.asList(eventDetails.getString("price")));
            ArrayList ticketsStatus = new ArrayList<>(Arrays.asList(eventDetails.getString("ticketsStatus")));
            ArrayList buyTicketsAt = new ArrayList<>(Arrays.asList(eventDetails.getString("buyTicketAt")));
            EDRecyclerViewAdapter adapter = new EDRecyclerViewAdapter(artistNames, venues, dates, times, genres, priceRanges, ticketsStatus, buyTicketsAt);
            recyclerView.setAdapter(adapter);

        } catch(Exception e){
            System.out.println("Error when dealing with onViewCreated in EDSection");
            e.printStackTrace();
        }





//        String[] items = getResources().getStringArray(R.array.tab_A);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(items);
//        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);

    }



    public class EDViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        TextView date;
        TextView time;
        TextView category;
        TextView venue;
        ImageView icon;
        public EDViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            name = (TextView) itemView.findViewById(R.id.eventName);
            icon = (ImageView) itemView.findViewById(R.id.eventIcon);
            date = (TextView) itemView.findViewById(R.id.eventDate);
            time = (TextView) itemView.findViewById(R.id.eventTime);
            category = (TextView) itemView.findViewById(R.id.eventCategory);
            venue = (TextView) itemView.findViewById(R.id.venueName);

        }
    }


}
