package com.example.baytalmuqadas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.baytalmuqadas.R;

import org.json.JSONException;
import org.json.JSONObject;

public class PrayerTimingsFragment extends Fragment {


    TextView fajr,dhuhr,asr,maghib,isha,date;

    public PrayerTimingsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prayer_timings, container, false);


        fajr = view.findViewById(R.id.fajr);
        dhuhr = view.findViewById(R.id.dhuhr);
        asr = view.findViewById(R.id.asr);
        maghib = view.findViewById(R.id.maghib);
        isha = view.findViewById(R.id.isha);
        date = view.findViewById(R.id.date);
        String URL_prayer_timings = "https://muslimsalat.com/gaza.json?key=1d8680cf4e2c67fe8fa48f253058a40f";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,URL_prayer_timings,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String t_fajr = response.getJSONArray("items").getJSONObject(0).get("fajr").toString();
                            String t_dhuhr = response.getJSONArray("items").getJSONObject(0).get("dhuhr").toString();
                            String t_asr = response.getJSONArray("items").getJSONObject(0).get("asr").toString();
                            String t_maghib = response.getJSONArray("items").getJSONObject(0).get("maghrib").toString();
                            String t_isha = response.getJSONArray("items").getJSONObject(0).get("isha").toString();
                            String t_date = response.getJSONArray("items").getJSONObject(0).get("date_for").toString();

                            fajr.setText(t_fajr);
                            dhuhr.setText(t_dhuhr);
                            asr.setText(t_asr);
                            maghib.setText(t_maghib);
                            isha.setText(t_isha);
                            date.setText(t_date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
        return view;
    }



    }
