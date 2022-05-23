package com.example.baytalmuqadas.adapters;

import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baytalmuqadas.AyaActivity;
import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.models.SurahModel;

import java.util.List;

public class SurahAdapters extends RecyclerView.Adapter<SurahAdapters.ViewHolder> {


    private List<SurahModel> surahModelLists;

    public SurahAdapters(List<SurahModel> surahModelLists) {
        this.surahModelLists = surahModelLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_surah,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.num_surah.setText(surahModelLists.get(position).getNumber());
        holder.small_nama.setText(surahModelLists.get(position).getEnglishName());
        holder.type.setText(surahModelLists.get(position).getRevelationType());
        holder.num_of_aya.setText(surahModelLists.get(position).getNumberOfAyahs());
        holder.text_aya.setText(surahModelLists.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ayatActivity = new Intent(holder.itemView.getContext(), AyaActivity.class);
                ayatActivity.putExtra("number",surahModelLists.get(holder.getAdapterPosition()).getNumber());
                ayatActivity.putExtra("name",surahModelLists.get(holder.getAdapterPosition()).getName());

                holder.itemView.getContext().startActivity(ayatActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return surahModelLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView num_surah,small_nama,type,num_of_aya,text_aya;
        private CardView cardview_surah;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            num_surah = itemView.findViewById(R.id.num_surah);
            small_nama = itemView.findViewById(R.id.small_nama);
            type = itemView.findViewById(R.id.type);
            num_of_aya = itemView.findViewById(R.id.num_of_aya);
            text_aya = itemView.findViewById(R.id.text_aya);
            cardview_surah = itemView.findViewById(R.id.cardview_surah);
        }
    }
}
