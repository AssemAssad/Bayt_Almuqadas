package com.example.baytalmuqadas.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.models.SuratModel;

import java.util.List;

public class SuratAdapters extends RecyclerView.Adapter<SuratAdapters.ViewHolder> {

    private List<SuratModel> suratModelLists;

    public SuratAdapters(List<SuratModel> suratModelLists) {
        this.suratModelLists = suratModelLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_surat,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.nomor_surat.setText(suratModelLists.get(position).getNomor_surat());
        holder.nama.setText(suratModelLists.get(position).getNama());
        holder.type.setText(suratModelLists.get(position).getType());
        holder.jumlah_ayat.setText(suratModelLists.get(position).getJumlah_ayat());
        holder.asma.setText(suratModelLists.get(position).getAsma());
    }

    @Override
    public int getItemCount() {
        return suratModelLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

       private TextView nomor_surat,nama,type,jumlah_ayat,asma;
       private CardView cardview_surat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nomor_surat = itemView.findViewById(R.id.nomor_surat);
            nama = itemView.findViewById(R.id.nama);
            type = itemView.findViewById(R.id.type);
            jumlah_ayat = itemView.findViewById(R.id.jumlah_ayat);
            asma = itemView.findViewById(R.id.asma);
            cardview_surat = itemView.findViewById(R.id.cardview_surat);
        }
    }
}
