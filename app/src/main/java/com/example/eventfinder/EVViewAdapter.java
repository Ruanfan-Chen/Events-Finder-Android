package com.example.eventfinder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class EDViewAdapter extends RecyclerView.Adapter{
    ArrayList artists;
    ArrayList venues;
    ArrayList eventDates;
    ArrayList eventTimes;

    ArrayList genres;
    ArrayList priceRanges;
    ArrayList ticketStatus;
    ArrayList buyTicketsAt;
    Context context;
    ArrayList imgUrls;
    private LayoutInflater layoutInflater;


    public EDViewAdapter(Context context, ArrayList artists, ArrayList venues, ArrayList eventDates, ArrayList eventTimes, ArrayList genres, ArrayList priceRanges, ArrayList ticketStatus, ArrayList buyTicketsAt, ArrayList imgUrls) {
        this.context = context;
        this.artists = artists;
        this.venues = venues;
        this.eventDates = eventDates;
        this.eventTimes = eventTimes;
        this.genres = genres;
        this.priceRanges = priceRanges;
        this.ticketStatus = ticketStatus;
        this.buyTicketsAt = buyTicketsAt;
        this.layoutInflater = LayoutInflater.from(context);
        this.imgUrls = imgUrls;
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=layoutInflater.inflate(R.layout.event_details_layout, parent, false);
        return new EDViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ((EDViewHolder) holder).edArtistTeams.setText((CharSequence) artists.get(position));
        ((EDViewHolder) holder).edVenueName.setText((CharSequence) venues.get(position));
        ((EDViewHolder) holder).edDate.setText((CharSequence) eventDates.get(position));
        ((EDViewHolder) holder).edTime.setText((CharSequence) eventTimes.get(position));
        ((EDViewHolder) holder).edGenres.setText((CharSequence) genres.get(position));
        ((EDViewHolder) holder).edPriceRange.setText((CharSequence) priceRanges.get(position));
        ((EDViewHolder) holder).edTicketStatus.setText((CharSequence) ticketStatus.get(position));
        ((EDViewHolder) holder).edBuyTicketsAt.setText((CharSequence) buyTicketsAt.get(position));

        if (!imgUrls.get(position).equals(""))
            Picasso.get().load((String) imgUrls.get(position)).into(((EDViewHolder) holder).edSeatmap);
    }


    public class EDViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView edArtistTeams;
        TextView edDate;
        TextView edTime;
        TextView edGenres;
        TextView edVenueName;
        TextView edPriceRange;

        TextView edTicketStatus;
        TextView edBuyTicketsAt;
        ImageView edSeatmap;
        public EDViewHolder(View itemView) {
            super(itemView);
            // get the reference of item view's
            edArtistTeams = (TextView) itemView.findViewById(R.id.edArtistTeams);

            edDate = (TextView) itemView.findViewById(R.id.edDate);
            edTime = (TextView) itemView.findViewById(R.id.edTime);
            edGenres = (TextView) itemView.findViewById(R.id.edGenres);
            edPriceRange = (TextView) itemView.findViewById(R.id.edPriceRange);
            edTicketStatus = (TextView) itemView.findViewById(R.id.edTicketStatus);
            edVenueName = (TextView) itemView.findViewById(R.id.edVenueName);
            edBuyTicketsAt = (TextView) itemView.findViewById(R.id.edBuyTicketsAt);

            edSeatmap = (ImageView) itemView.findViewById(R.id.edSeatmap);

        }
    }


}
