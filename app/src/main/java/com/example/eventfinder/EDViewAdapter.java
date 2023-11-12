package com.example.eventfinder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

public class EDSection extends Section{
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

    public EDSection(ArrayList artists, ArrayList venues, ArrayList eventDates, ArrayList eventTimes, ArrayList genres, ArrayList priceRanges,  ArrayList ticketStatus, ArrayList buyTicketsAt) {

        super(SectionParameters.builder()
                .itemResourceId(R.layout.event_details_layout)
                .build());

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
    public int getContentItemsTotal() {
        return venues.size();
    }

    @Override
    public RecyclerView.ViewHolder getItemViewHolder(final View view) {

        // set the view's size, margins, paddings and layout parameters
        EDSection.EDViewHolder vh = new EDSection.EDViewHolder(view); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edArtistTeams.setText((CharSequence) artists.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edVenueName.setText((CharSequence) venues.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edDate.setText((CharSequence) eventDates.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edTime.setText((CharSequence) eventTimes.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edGenres.setText((CharSequence) genres.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edPriceRange.setText((CharSequence) priceRanges.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edTicketStatus.setText((CharSequence) ticketStatus.get(position));
        ((EDRecyclerViewAdapter.EDViewHolder) holder).edBuyTicketsAt.setText((CharSequence) buyTicketsAt.get(position));

        if (!imgUrls.get(position).equals(""))
            Picasso.get().load((String) imgUrls.get(position)).into(((EDRecyclerViewAdapter.EDViewHolder) holder).edSeatmap);
    }

//    @Override
//    public RecyclerView.ViewHolder getHeaderViewHolder(final View view) {
//        return new HeaderViewHolder(view);
//    }
//
//    @Override
//    public void onBindHeaderViewHolder(final RecyclerView.ViewHolder holder) {
//        final HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
//
//        headerHolder.tvTitle.setText(title);
//    }

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
