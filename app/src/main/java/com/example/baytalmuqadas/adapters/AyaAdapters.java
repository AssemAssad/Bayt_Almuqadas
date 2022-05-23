package com.example.baytalmuqadas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.models.AyaModel;

import java.util.List;

public class AyaAdapters extends RecyclerView.Adapter<AyaAdapters.ViewHolder> {

    private List<AyaModel> ayaModelList;

    public AyaAdapters(List<AyaModel> ayaModelList) {
        this.ayaModelList = ayaModelList;
    }

    @NonNull
    @Override
    public AyaAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_aya,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AyaAdapters.ViewHolder holder, int position) {


        holder.num_aya.setText(ayaModelList.get(position).getId());
        holder.ar_aya.setText(ayaModelList.get(position).getArabic_text());
        holder.en_aya.setText(ayaModelList.get(position).getTranslation());



    }

    @Override
    public int getItemCount() {
        return ayaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView num_aya,ar_aya,en_aya ;
        CardView cardview_aya;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num_aya = itemView.findViewById(R.id.num_aya);
            ar_aya = itemView.findViewById(R.id.ar_aya);
            en_aya = itemView.findViewById(R.id.en_aya);
            cardview_aya = itemView.findViewById(R.id.cardview_aya);
        }
    }
}
