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
import com.example.baytalmuqadas.models.DuasModel2;

import java.util.List;

public class DuasAdapter2 extends RecyclerView.Adapter<DuasAdapter2.ViewHolder>{

    private List<DuasModel2> duasModel2List;

    public DuasAdapter2(List<DuasModel2> duasModel2List) {
        this.duasModel2List = duasModel2List;
    }

    @NonNull
    @Override
    public DuasAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_duas2,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuasAdapter2.ViewHolder holder, int position) {

        holder.tv_count.setText(duasModel2List.get(position).getId());
        holder.tv_duas_text.setText(duasModel2List.get(position).getText());
        holder.tv_number_repeat.setText(duasModel2List.get(position).getRepeat());


        if (position % 2 != 0)holder.cardView_duas2.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.colorBackground));
        else holder.cardView_duas2.setCardBackgroundColor(Color.WHITE);

    }

    @Override
    public int getItemCount() {
        return duasModel2List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_duas_text,tv_count,tv_number_repeat;
        private CardView cardView_duas2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_count = itemView.findViewById(R.id.count);
            tv_duas_text = itemView.findViewById(R.id.text_duas);
            tv_number_repeat = itemView.findViewById(R.id.num_duas);
            cardView_duas2 = itemView.findViewById(R.id.cardview_duas2);
        }
    }
}
