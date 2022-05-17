package com.example.baytalmuqadas.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.models.AyatModel;

import java.util.List;

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.ViewHolder> {

    private List<AyatModel> ayatModelList;

    public AyatAdapter(List<AyatModel> ayatModelList) {
        this.ayatModelList = ayatModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ayat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nomor_ayat.setText(ayatModelList.get(position).getNomor_ayat());
        holder.ar.setText(ayatModelList.get(position).getAr());
        holder.id.setText(ayatModelList.get(position).getId());

        if (position % 2 != 0)holder.cardview_ayat.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorBackground));
        else holder.cardview_ayat.setCardBackgroundColor(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return ayatModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ar,id,nomor_ayat ;
        CardView cardview_ayat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ar = itemView.findViewById(R.id.ar);
            id = itemView.findViewById(R.id.id);
            nomor_ayat = itemView.findViewById(R.id.nomor_ayat);
            cardview_ayat = itemView.findViewById(R.id.cardview_ayat);

        }
    }
}
