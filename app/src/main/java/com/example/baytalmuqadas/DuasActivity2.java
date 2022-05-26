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
import com.example.baytalmuqadas.adapters.DuasAdapter;
import com.example.baytalmuqadas.adapters.DuasAdapter2;
import com.example.baytalmuqadas.models.DuasModel;
import com.example.baytalmuqadas.models.DuasModel2;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DuasActivity2 extends AppCompatActivity {

    private List<DuasModel2> duasModel2List;
    private RecyclerView recyclerview_duas2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duas2);
        String title = getIntent().getStringExtra("title");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String id = getIntent().getStringExtra("id");
        duasModel2List = new ArrayList<>();
        String url = "https://www.hisnmuslim.com/api/ar/"+id+".json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 10;
                for (int i = 0;i < count;i++){
                    DuasModel2 duasModel2 = new DuasModel2();
                    try {
                        int j = i+1;
                        duasModel2.setText(response.getJSONArray(title).getJSONObject(i).getString("ARABIC_TEXT"));
                        duasModel2.setId(""+j);
                        duasModel2.setRepeat(response.getJSONArray(title).getJSONObject(i).getString("REPEAT"));
                        duasModel2List.add(duasModel2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                recyclerview_duas2 = findViewById(R.id.recyclerview_duas2);
                recyclerview_duas2.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerview_duas2.setHasFixedSize(true);
                recyclerview_duas2.setAdapter(new DuasAdapter2(duasModel2List));
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