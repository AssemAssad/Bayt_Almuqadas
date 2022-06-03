package com.example.baytalmuqadas.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.baytalmuqadas.R;

public class TasbihFragment extends Fragment {

TextView tv_count , tv_spinner;
Button btn_click,btn_reseat ;
int count = 0;
Spinner spinner;
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
        spinner = view.findViewById(R.id.spinner);
        tv_spinner = view.findViewById(R.id.spinner_item);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(getContext(),R.array.spinner, R.layout.color_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdwon);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                tv_spinner.setText(text);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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