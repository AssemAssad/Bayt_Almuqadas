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

import com.example.baytalmuqadas.R;
import com.example.baytalmuqadas.models.DuasModel;

import java.util.List;

public class DuasAdapter extends RecyclerView.Adapter<DuasAdapter.ViewHolder>{

    private List<DuasModel> duasModelList;

    public DuasAdapter(List<DuasModel> duasModelList) {
        this.duasModelList = duasModelList;
    }

    @NonNull
    @Override
    public DuasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_duas,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuasAdapter.ViewHolder holder, int position) {

        holder.tv_duas.setText(duasModelList.get(position).getTitle());

        if (position % 2 != 0)holder.cardview_duas.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorBackground));
        else holder.cardview_duas.setCardBackgroundColor(Color.WHITE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent duasActivity = new Intent(holder.itemView.getContext(), com.example.baytalmuqadas.DuasActivity2.class);
                duasActivity.putExtra("id",duasModelList.get(holder.getAdapterPosition()).getId());
                duasActivity.putExtra("title",duasModelList.get(holder.getAdapterPosition()).getTitle());
                holder.itemView.getContext().startActivity(duasActivity);



            }
        });

    }

    @Override
    public int getItemCount() {
        return duasModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_duas;
        private CardView cardview_duas;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_duas = itemView.findViewById(R.id.tv_duas);
            cardview_duas = itemView.findViewById(R.id.cardview_duas);
        }
    }
}
