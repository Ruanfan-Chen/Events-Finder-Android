package com.example.eventfinder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.ArrayList;

public class EventDetailsAdapter extends RecyclerView.Adapter  {



    Context context;
    JSONObject eventDetails;
    ArrayList artistsDetails;
    JSONObject venueDetails;
    ArrayList artistsNames;




    private final String backendUrl = "https://csci571hw8server-382411.wl.r.appspot.com/";
    public EventDetailsAdapter(Context context, JSONObject eventDetails, ArrayList artistsDetails, JSONObject venueDetails, ArrayList artistsNames) {
        this.context = context;
        this.eventDetails = eventDetails;
        this.artistsDetails = artistsDetails;
        this.venueDetails = venueDetails;
        this.artistsNames = artistsNames;


    }
    @Override
    public EventDetailsAdapter.EventDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // infalte the item Layout
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_table_row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        EventDetailsAdapter.EventDetailsViewHolder vh = new EventDetailsAdapter.EventDetailsViewHolder(v); // pass the view to View Holder
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        // set the data in items
//        ((EventsTableAdapter.EventsTableViewHolder) holder).name.setText((CharSequence) eventNames.get(position));
//        ((EventsTableAdapter.EventsTableViewHolder) holder).date.setText((CharSequence) eventDates.get(position));
//        ((EventsTableAdapter.EventsTableViewHolder) holder).time.setText((CharSequence) eventTimes.get(position));
//        ((EventsTableAdapter.EventsTableViewHolder) holder).venue.setText((CharSequence) eventVenues.get(position));
//        ((EventsTableAdapter.EventsTableViewHolder) holder).category.setText((CharSequence) eventCategories.get(position));
//        if (!eventIcons.get(position).equals(""))
//            Picasso.get().load((String) eventIcons.get(position)).into(((EventsTableAdapter.EventsTableViewHolder) holder).icon);
//        // implement setOnClickListener event on item view.
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // TODO: get event details, launch a new activity
//                RequestQueue queue = Volley.newRequestQueue(context);
//                String url = backendUrl + "getEventById?id=" + eventIds.get(position);
//                System.out.println("get event details url = " + url);
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                // Display the first 500 characters of the response string.
////                        textView.setText("Response is: " + response.substring(0,500));
//                                System.out.println("get event details response = " + response);
//                                try {
////                            String [] eventsStrings = (response.substring(1, response.length()-1)).split("\\{");
////                            System.out.println("need to deal with eventsStrings = " + Arrays.toString(eventsStrings));
////                            for (int i = 1; i < eventsStrings.length; i++){
////                                JSONObject eventObject = new JSONObject( "{" + eventsStrings[i]);
////                                System.out.println("event = " + eventObject);
////                                System.out.println("url =" + eventObject.getString("icon"));
////                                events.add(eventObject);
////                            }
////                            System.out.println("get events = " + events);
//                                    Intent i = new Intent(context, EventDetailsActivity.class);
//                                    i.putExtra("eventDetails", response);
//                                    context.startActivity(i);
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }
//                                //IMPORTANT: set data here and notify
//
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("Error when getting keyword auto complete:");
//                        error.printStackTrace();
//                    }
//                });
//
//// Add the request to the RequestQueue.
//                queue.add(stringRequest);
//
//
//            }
//        });
    }
    @Override
    public int getItemCount() {
        return artistsDetails.size();
    }
    public class EventDetailsViewHolder extends RecyclerView.ViewHolder {
        // init the item view's
        TextView name;
        TextView date;
        TextView time;
        TextView category;
        TextView venue;
        ImageView icon;
        public EventDetailsViewHolder(View itemView) {
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
