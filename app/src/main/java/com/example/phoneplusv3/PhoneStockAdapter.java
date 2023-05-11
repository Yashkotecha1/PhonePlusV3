package com.example.phoneplusv3;

import android.annotation.SuppressLint;
import android.content.Context;
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

import java.util.List;

public class PhoneStockAdapter extends RecyclerView.Adapter<PhoneStockAdapter.ViewHolder> {

    private List<PhoneStockModel> phoneStockModel;


    private PhoneStockModel stockModel;
    private AddPhoneDao dataDao;

    Context context;


    public PhoneStockAdapter(List<PhoneStockModel> phoneStockModel) {
        this.phoneStockModel = phoneStockModel;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
        View view = inflater.inflate(R.layout.soldstock, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        AppDb employeeDb = Room.databaseBuilder(parent.getContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.addPhoneDao();

        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        holder.name.setText("" + phoneStockModel.get(position).getBrandName() + " " + phoneStockModel.get(position).getBrandType());
        holder.price.setText("Price- \t\t" + phoneStockModel.get(position).getPrice()+"₹");
        holder.qty.setText("Qty- \t\t" + phoneStockModel.get(position).getQty());
        holder.imei.setText("Imei- \t\t" + phoneStockModel.get(position).getImeiNumber());
        if (phoneStockModel.get(position).getBrandName().equals("Iphone")) {
            holder.memory.setText("" + phoneStockModel.get(position).getMemory() + " GB");
        } else {

            holder.memory.setText("Storage-\t\t " + phoneStockModel.get(position).getRam() + " / " + phoneStockModel.get(position).getMemory()+" GB");
        }
        holder.color.setText("Color-\t\t" + phoneStockModel.get(position).getColor());
        holder.totalNetPrice.setText("Total: \t" + phoneStockModel.get(position).getPrice() * phoneStockModel.get(position).getQty()+"₹");
        holder.date.setText("Date- \t" + phoneStockModel.get(position).getDate());

        holder.img.setImageDrawable(Drawable.createFromPath(String.valueOf(phoneStockModel.get(position).getImg())));


        holder.sold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), SoldStockDialog.class);
                i.putExtra("id", phoneStockModel.get(position).getId());
                view.getContext().startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return phoneStockModel.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, qty, price, imei, Soldprice, dateTime, totalNetPrice, color, memory, date;
        ImageView img;
        Button sold;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
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
            date = itemView.findViewById(R.id.date);
        }
    }
}

