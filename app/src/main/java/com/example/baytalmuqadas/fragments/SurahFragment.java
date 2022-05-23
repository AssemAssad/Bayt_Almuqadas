package com.example.baytalmuqadas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.adapters.SurahAdapters;
import com.example.baytalmuqadas.models.SurahModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SurahFragment extends Fragment {

    private List<SurahModel> surahModelList;
    private RecyclerView recyclerview_surah;

    public SurahFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surah, container, false);
        surahModelList = new ArrayList<>();
        String URL_SURAH = "https://api.alquran.cloud/v1/surah";

        JsonObjectRequest jsonObjectRequest =  new JsonObjectRequest(Request.Method.GET, URL_SURAH,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        int cont = 0;
                        try {
                            cont = response.getJSONArray("data").length();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for (int i = 0 ; i < cont ; i++ ){
                            SurahModel surahModel = new SurahModel();

                            try {
                                surahModel.setNumber(response.getJSONArray("data").getJSONObject(i).getString("number"));
                                surahModel.setName(response.getJSONArray("data").getJSONObject(i).getString("name"));
                                surahModel.setEnglishName(response.getJSONArray("data").getJSONObject(i).getString("englishName"));
                                surahModel.setNumberOfAyahs(response.getJSONArray("data").getJSONObject(i).getString("numberOfAyahs"));
                                surahModel.setRevelationType(response.getJSONArray("data").getJSONObject(i).getString("revelationType"));
                                surahModelList.add(surahModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        recyclerview_surah = view.findViewById(R.id.recyclerview_surah);
                        recyclerview_surah.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerview_surah.setHasFixedSize(true);
                        recyclerview_surah.setAdapter(new SurahAdapters(surahModelList));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonObjectRequest);
        return view ;
    }


}

