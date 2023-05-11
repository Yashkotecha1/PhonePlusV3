package com.example.phoneplusv3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.phoneplusv3.AppDb;
import com.example.phoneplusv3.PhoneStockModel;
import com.example.phoneplusv3.R;
import com.example.phoneplusv3.SoldStockDao;
import com.example.phoneplusv3.SoldStockModel;

import java.util.List;

public class SoldStockAdapter
        extends RecyclerView.Adapter<SoldStockAdapter.ViewHolder> {

    private List<SoldStockModel> soldStockModels;

    private PhoneStockModel stockModel;
    private SoldStockDao dataDao;



    public SoldStockAdapter(List<SoldStockModel> soldStockModels) {
        this.soldStockModels = soldStockModels;
    }

    @NonNull
    @Override
    public SoldStockAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.soldstock, parent, false);
        SoldStockAdapter.ViewHolder viewHolder = new SoldStockAdapter.ViewHolder(view);
        AppDb employeeDb = Room.databaseBuilder(parent.getContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.soldStockDao();

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SoldStockAdapter.ViewHolder holder, int position) {

        holder.name.setText(""+soldStockModels.get(position).getBrandName()+" "+soldStockModels.get(position).getBrandType());
        holder.price.setText("Price: \t"+soldStockModels.get(position).getPrice()+"₹");
        holder.qty.setText("Qty: \t"+soldStockModels.get(position).getQty());
        holder.imei.setText("Imei: \t"+soldStockModels.get(position).getImeiNumber());
        if (soldStockModels.get(position).getBrandName().equals("Apple")) {
            holder.memory.setText("" + soldStockModels.get(position).getMemory() + " GB");
        } else {

            holder.memory.setText("" + soldStockModels.get(position).getRam() + " / " + soldStockModels.get(position).getMemory());
        }
        holder.color.setText("Color: \t"+soldStockModels.get(position).getColor());
        holder.img.setImageDrawable(Drawable.createFromPath(String.valueOf(soldStockModels.get(position).getImg())));
        holder.totalNetPrice.setText("Total price: \t"+soldStockModels.get(position).getSoldPrice()*soldStockModels.get(position).getQty()+"₹");

        holder.dateTime.setText("Date: \t"+soldStockModels.get(position).getDate());

        if (soldStockModels.get(position).getSoldPrice() != 0)
        {
            holder.Soldprice.setVisibility(View.VISIBLE);
            holder.Soldprice.setText("Sold Price: \t"+soldStockModels.get(position).getSoldPrice()+"₹");
        }

        holder.sold.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return soldStockModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,qty,price,imei,Soldprice,dateTime,totalNetPrice,color,memory;

        Button sold;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name =itemView.findViewById(R.id.name);
            qty = itemView.findViewById(R.id.qty);
            price = itemView.findViewById(R.id.price);
            imei = itemView.findViewById(R.id.imei);
            img = itemView.findViewById(R.id.img);
            sold = itemView.findViewById(R.id.sold);
            Soldprice = itemView.findViewById(R.id.Soldprice);
            dateTime = itemView.findViewById(R.id.dateTime);
            totalNetPrice = itemView.findViewById(R.id.totalNetPrice);
            color = itemView.findViewById(R.id.color);
            memory = itemView.findViewById(R.id.memory);

        }
    }
}