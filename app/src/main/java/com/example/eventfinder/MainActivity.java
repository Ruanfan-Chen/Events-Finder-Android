package com.example.eventfinder;

import android.content.Intent;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.eventfinder.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AutoCompleteAdapter autoCompleteAdapter;
    private Handler handler;

    private static final int TRIGGER_AUTO_COMPLETE = 100;
    private static final long AUTO_COMPLETE_DELAY = 300;
    private final String backendUrl = "https://csci571hw8server-382411.wl.r.appspot.com/";
    private final String ipInfoAPI = "https://ipinfo.io/?token=47fca5ebd7b4ba";

    private final String googleGeoEncodingAPI = "https://maps.googleapis.com/maps/api/geocode/json?";
    private final String geoEncodingKey = "AIzaSyBucaSaD2HEihjHeUct4hH52uav2ZX6wiU";
    private String latLng;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = binding.viewPager;
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = binding.tabs;
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = binding.fab;
//
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        // auto-complete functionality source: https://www.truiton.com/2018/06/android-autocompletetextview-suggestions-from-webservice-call/
        final AutoCompleteTextView autoCompleteTextView =
                findViewById(R.id.keyword);
//        final TextView selectedText = findViewById(R.id.selected_item);
        //Setting up the adapter for AutoSuggest
        System.out.println("111");
        autoCompleteAdapter = new AutoCompleteAdapter(this,
                android.R.layout.simple_dropdown_item_1line);
        System.out.println("222");
        autoCompleteTextView.setThreshold(2);
        autoCompleteTextView.setAdapter(autoCompleteAdapter);
        autoCompleteTextView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
//                        selectedText.setText(autoCompleteAdapter.getObject(position));
                    }
                });
        System.out.println(333);
        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int
                    count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                handler.removeMessages(TRIGGER_AUTO_COMPLETE);
                handler.sendEmptyMessageDelayed(TRIGGER_AUTO_COMPLETE,
                        AUTO_COMPLETE_DELAY);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TRIGGER_AUTO_COMPLETE) {
                    if (!TextUtils.isEmpty(autoCompleteTextView.getText())) {
                        getKeywordAutoSuggestion(autoCompleteTextView.getText().toString());
                    }
                }
                return false;
            }
        });
        System.out.println(444);

        Switch onOffSwitch = (Switch)  findViewById(R.id.autoDetectSwitch);
        onOffSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println("Switch State = "+isChecked);
                if (isChecked){
                    getIpInfoLocation();
                }

            }

        });
    }

    private void getIpInfoLocation(){
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println("url = " + ipInfoAPI);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, ipInfoAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0,500));

                        List<String> stringList = new ArrayList<>();
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            System.out.println("responseObject = " + responseObject);
                            latLng = responseObject.getString("loc");
                            System.out.println("latLng now set to = "+ latLng);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error when getting auto location:");
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    private void getLatLngByAddress(String address){
        String url = googleGeoEncodingAPI + "address=" + address + "&key=" + geoEncodingKey;
        RequestQueue queue = Volley.newRequestQueue(this);
        System.out.println("url = " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0,500));

                        List<String> stringList = new ArrayList<>();
                        try {
                            JSONObject responseObject = new JSONObject(response);
                            System.out.println("responseObject = " + responseObject);
                            JSONArray array = responseObject.getJSONArray("results");
                            JSONObject location = ((JSONObject) array.get(0)).getJSONObject("geometry").getJSONObject("location");
                            latLng = location.getString("lat") + "," + location.getString("lng");

//                            System.out.println("latLng now set to = "+ latLng);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error when getting auto location:");
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void getKeywordAutoSuggestion(String keyword) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = backendUrl + "keywordSearch?keyword=" + keyword;
        System.out.println("url = " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0,500));

                        List<String> stringList = new ArrayList<>();
                        try {
                            System.out.println(66666);
                            int n = ((String) response).length();
                            String [] suggestions = response.substring(1, n-2).split(",");
                            for (int i = 0; i < suggestions.length; i++) {
                                stringList.add(suggestions[i].substring(1, suggestions[i].length()-1));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //IMPORTANT: set data here and notify
                        autoCompleteAdapter.setData(stringList);
                        autoCompleteAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error when getting keyword auto complete:");
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public void onSubmitBtnClick(View view){
        String keyword = ((AutoCompleteTextView) findViewById(R.id.keyword)).getText().toString();
        String d = ((EditText) findViewById(R.id.distance)).getText().toString();
        String distance = d.equals("") ? "10" : d;
        String category = ((Spinner) findViewById(R.id.category)).getSelectedItem().toString();
        if (category.equals("All"))
            category = "Default";
        String location = ((EditText) findViewById(R.id.location)).getText().toString();
        System.out.println("keyword = " + keyword);
        System.out.println("distance = " + distance);
        System.out.println("category = " + category);
        System.out.println("location = " + location);
        if (!location.equals(""))
            getLatLngByAddress(location);
        keyword = String.join("%20", keyword.split(" "));

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = backendUrl + "getEventsByData?keyword=" + keyword + "&distance=" + distance + "&category=" + category + "&location=" + latLng;
        System.out.println("url = " + url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
//                        textView.setText("Response is: " + response.substring(0,500));
                        System.out.println("response = " + response);
                        List<JSONObject> events = new ArrayList<>();
                        try {
//                            String [] eventsStrings = (response.substring(1, response.length()-1)).split("\\{");
//                            System.out.println("need to deal with eventsStrings = " + Arrays.toString(eventsStrings));
//                            for (int i = 1; i < eventsStrings.length; i++){
//                                JSONObject eventObject = new JSONObject( "{" + eventsStrings[i]);
//                                System.out.println("event = " + eventObject);
//                                System.out.println("url =" + eventObject.getString("icon"));
//                                events.add(eventObject);
//                            }
//                            System.out.println("get events = " + events);
                            Intent i = new Intent(MainActivity.this,EventTableActivity.class);
                            i.putExtra("events", response);
                            startActivity(i);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //IMPORTANT: set data here and notify

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error when getting keyword auto complete:");
                error.printStackTrace();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }


}