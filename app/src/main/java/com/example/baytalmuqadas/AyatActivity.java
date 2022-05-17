package com.example.baytalmuqadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.baytalmuqadas.adapters.AyatAdapter;
import com.example.baytalmuqadas.adapters.SuratAdapters;
import com.example.baytalmuqadas.models.AyatModel;
import com.example.baytalmuqadas.utils.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class AyatActivity extends AppCompatActivity {


    private List<AyatModel> ayatModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayat);
        getSupportActionBar().setTitle(getIntent().getStringExtra("NAMA"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getAyatFromApi();
    }

    private void getAyatFromApi(){
        ayatModelList = new ArrayList<>();
        String URL = Util.URL_AYAT + getIntent().getStringExtra("NOMOR_SURAT");

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d("Response",response.toString());

                        int jumlahAyat = response.length();

                        for (int i = 0; i < jumlahAyat;i++){
                            AyatModel ayatModel = new AyatModel();
                            try {
                                ayatModel.setNomor_ayat(response.getJSONObject(i).getString("nomor"));
                                ayatModel.setAr(response.getJSONObject(i).getString("ar"));
                                ayatModel.setId(response.getJSONObject(i).getString("id"));

                                ayatModelList.add(ayatModel);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        RecyclerView recyclerview_ayat =  findViewById(R.id.recyclerview_ayat);
                        recyclerview_ayat.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        recyclerview_ayat.setHasFixedSize(true);
                        recyclerview_ayat.setAdapter(new AyatAdapter(ayatModelList));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}