package com.example.phoneplusv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SoldStockActivity extends AppCompatActivity {

    RecyclerView recycleId;
    private SoldStockDao dataDao ;
    com.example.phoneplusv2.SoldStockAdapter soldStockAdapter;
    List<SoldStockModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_stock);
        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.soldStockDao();


        recycleId = findViewById(R.id.recycleId);

        list.addAll(dataDao.getAll());
        if (list.size() == 0)
        {
            recycleId.setVisibility(View.GONE);
        }
        else {

            soldStockAdapter = new com.example.phoneplusv2.SoldStockAdapter(list);
            recycleId.setHasFixedSize(true);
            recycleId.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
            recycleId.setAdapter(soldStockAdapter);
        }
    }
}