package com.example.eventfinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;


public class EDFragment extends Fragment {
    RecyclerView recyclerView;
    JSONObject eventDetails;
    ArrayList artistNames;

    public EDFragment(JSONObject eventDetails, ArrayList artistNames){
        this.eventDetails = eventDetails;
        this.artistNames = artistNames;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.event_details2, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.edRecyclerView2);
        System.out.println("ED: inflated !! ");



        try {
            ArrayList venues = new ArrayList<>(Arrays.asList(eventDetails.getString("venue")));
            String [] dt = eventDetails.getString("dt").split(" ");

            ArrayList dates =  new ArrayList<>(Arrays.asList(dt[0]));
            ArrayList times = new ArrayList<>(dt.length > 1? Arrays.asList(dt[1]): Arrays.asList(""));
            ArrayList genres = new ArrayList<>(Arrays.asList(eventDetails.getString("genres")));
            ArrayList priceRanges = new ArrayList<>(Arrays.asList(eventDetails.getString("price")));
            ArrayList ticketsStatus = new ArrayList<>(Arrays.asList(eventDetails.getString("ticketStatus")));
            ArrayList buyTicketsAt = new ArrayList<>(Arrays.asList(eventDetails.getString("buyTicketAt")));
            ArrayList imgUrls = new ArrayList<>(Arrays.asList(eventDetails.getString("seatmap")));
//            EDRecyclerViewAdapter adapter = new EDRecyclerViewAdapter(artistNames, venues, dates, times, genres, priceRanges, ticketsStatus, buyTicketsAt);
//            recyclerView.setAdapter(adapter);
            System.out.println("have a look at " + artistNames);
            System.out.println("then have a look at venues = " + venues);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            EDViewAdapter edViewAdapter = new EDViewAdapter(getActivity(), artistNames, venues, dates, times, genres, priceRanges, ticketsStatus, buyTicketsAt, imgUrls);
            recyclerView.setAdapter(edViewAdapter);
            System.out.println("ED Done: recyclerView.setAdapter(edViewAdapter), and recyclerView.getAdapter() = " + recyclerView.getAdapter());
        } catch(Exception e){
            System.out.println("Error when dealing with onViewCreated in EDFragment");
            e.printStackTrace();
        }

        return view;
    }

}
