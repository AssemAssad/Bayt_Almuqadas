package com.example.baytalmuqadas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.baytalmuqadas.R;

public class TasbihFragment extends Fragment {

TextView tv_count;
Button btn_click,btn_reseat ;
int count = 0;
    public TasbihFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tasbih, container, false);

        tv_count = view.findViewById(R.id.textView2);
        btn_click = view.findViewById(R.id.button);
        btn_reseat = view.findViewById(R.id.button2);

        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                tv_count.setText(""+count);
            }
        });

        btn_reseat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                tv_count.setText(""+count);
            }
        });


        return view;
    }
}