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
import com.example.baytalmuqadas.adapters.DuasAdapter;
import com.example.baytalmuqadas.models.DuasModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DuasFragment extends Fragment {


    private List<DuasModel> duasModelList;
    private RecyclerView recyclerView_duas;

    public DuasFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_duas, container, false);
        String URL_DUAS = "https://www.hisnmuslim.com/api/ar/husn_ar.json";
        duasModelList = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_DUAS, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int count = 132;
                for (int i = 0;i < count;i++){
                    DuasModel duasModel = new DuasModel();

                    try {
                        duasModel.setTitle(response.getJSONArray("العربية").getJSONObject(i).getString("TITLE"));
                        duasModel.setId(response.getJSONArray("العربية").getJSONObject(i).getString("ID"));
                        duasModelList.add(duasModel);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                recyclerView_duas =  view.findViewById(R.id.recyclerview_duas);
                recyclerView_duas.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView_duas.setHasFixedSize(true);
                recyclerView_duas.setAdapter(new DuasAdapter(duasModelList));
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