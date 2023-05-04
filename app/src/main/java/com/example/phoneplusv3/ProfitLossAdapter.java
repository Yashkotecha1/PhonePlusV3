package com.example.phoneplusv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class ProfitLossAdapter extends RecyclerView.Adapter<ProfitLossAdapter.ViewHolder> {


    private ProfitLossDao dataDao;
    List<ProfitLossModel> profitLossModel;

    public ProfitLossAdapter(List<ProfitLossModel> profitLossModel) {
        this.profitLossModel = profitLossModel;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.profit_loss_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        AppDb employeeDb = Room.databaseBuilder(parent.getContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.profitLossDao();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.brandName.setText(profitLossModel.get(position).getBrandName());
        holder.purchasePrice.setText(""+10000);
        holder.sellPrice.setText(""+15000);
        holder.profitLoss.setText(""+5000);
//        holder.brandName.setText(profitLossModel.get(position).getBrandName());
//        holder.purchasePrice.setText(profitLossModel.get(position).getPurchasePrice());
//        holder.sellPrice.setText(profitLossModel.get(position).getSellPrice());
//        holder.profitLoss.setText(profitLossModel.get(position).getSellPrice() - profitLossModel.get(position).getPurchasePrice());
    }

    @Override
    public int getItemCount() {
        return profitLossModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView brandName,purchasePrice,sellPrice,profitLoss,qty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.brandName);
            purchasePrice = itemView.findViewById(R.id.purchasePrice);
            sellPrice = itemView.findViewById(R.id.sellPrice);
            profitLoss = itemView.findViewById(R.id.profitLoss);
            qty = itemView.findViewById(R.id.qty);


        }
    }
}
