package com.example.baytalmuqadas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.baytalmuqadas.adapters.AyaAdapters;
import com.example.baytalmuqadas.models.AyaModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AyaActivity extends AppCompatActivity {


    private List<AyaModel> ayaModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aya);
        getSupportActionBar().setTitle(getIntent().getStringExtra("name"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getAyatFromApi();
    }

    private void getAyatFromApi() {

        ayaModelList = new ArrayList<>();
        String URL_AYA = "https://quranenc.com/api/translation/sura/english_saheeh/"+ getIntent().getStringExtra("number");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_AYA, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                int num_Aya = 0;
                try {
                    num_Aya = response.getJSONArray("result").length();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < num_Aya;i++) {
                    AyaModel ayaModel = new AyaModel();
                    try {
                        ayaModel.setId(response.getJSONArray("result").getJSONObject(i).getString("aya"));
                        ayaModel.setArabic_text(response.getJSONArray("result").getJSONObject(i).getString("arabic_text"));
                        ayaModel.setTranslation(response.getJSONArray("result").getJSONObject(i).getString("translation"));
                        ayaModelList.add(ayaModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                RecyclerView recyclerview_aya =  findViewById(R.id.recyclerview_aya);
                recyclerview_aya.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview_aya.setHasFixedSize(true);
                recyclerview_aya.setAdapter(new AyaAdapters(ayaModelList));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonObjectRequest);
    }
}