package com.example.eventfinder;

import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EDRecyclerViewAdapter extends RecyclerView.Adapter {
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

    public EDRecyclerViewAdapter(ArrayList artists, ArrayList venues, ArrayList eventDates, ArrayList eventTimes, ArrayList genres, ArrayList priceRanges,  ArrayList ticketStatus, ArrayList buyTicketsAt) {

        this.artists = artists;
        this.venues = venues;
        this.eventDates = eventDates;
        this.eventTimes = eventTimes;
        this.genres = genres;
        this.priceRanges = priceRanges;
        this.ticketStatus = ticketStatus;
        this.buyTicketsAt = buyTicketsAt;


    }
    @Override
    public EDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_details_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EDViewHolder vh = new EDViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // set the data in items
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
    @Override
    public int getItemCount() {
        return 1;
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
