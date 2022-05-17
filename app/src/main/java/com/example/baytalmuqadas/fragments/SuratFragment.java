package com.example.baytalmuqadas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.adapters.SuratAdapters;
import com.example.baytalmuqadas.models.SuratModel;
import com.example.baytalmuqadas.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SuratFragment extends Fragment {

    private List<SuratModel> suratModelList;
    private RecyclerView recyclerview_surat;

    public SuratFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSuratFromApi();

        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_surat, container, false);

        return view ;
    }

    private void getSuratFromApi(){

        suratModelList = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(Request.Method.GET, Util.URL_SURAT,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        int jumlahSueat = response.length();
                        for (int i = 0 ; i < jumlahSueat ; i++ ){
                            SuratModel suratModel = new SuratModel();
                            try {

                                suratModel.setNomor_surat(response.getJSONObject(i).getString("nomor"));
                                suratModel.setNama(response.getJSONObject(i).getString("nama"));
                                suratModel.setType(response.getJSONObject(i).getString("type").toUpperCase());
                                suratModel.setJumlah_ayat(response.getJSONObject(i).getString("ayat"));
                                suratModel.setAsma(response.getJSONObject(i).getString("asma"));
                                suratModel.setArti(response.getJSONObject(i).getString("arti"));
                                suratModel.setAudio(response.getJSONObject(i).getString("audio"));
                                suratModel.setKeterangan(response.getJSONObject(i).getString("keterangan"));
                                suratModelList.add(suratModel);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        recyclerview_surat = getView().findViewById(R.id.recyclerview_surat);
                        recyclerview_surat.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerview_surat.setHasFixedSize(true);
                        recyclerview_surat.setAdapter(new SuratAdapters(suratModelList));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(jsonArrayRequest);

    }
}

