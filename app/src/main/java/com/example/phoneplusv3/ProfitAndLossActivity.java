package com.example.phoneplusv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class ProfitAndLossActivity extends AppCompatActivity {
    RecyclerView recycleId;
    private SoldStockDao dataDao ;
    private AddPhoneDao addPhoneDao ;
    private ProfitLossDao profitLossDao ;
    ProfitLossAdapter soldStockAdapter;
    List<SoldStockModel> list = new ArrayList<>();
    List<PhoneStockModel> list1 = new ArrayList<>();
    List<ProfitLossModel> profitLossModels = new ArrayList<>();


    ProfitLossModel profitLossModel = new ProfitLossModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_and_loss);
        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.soldStockDao();
        addPhoneDao = employeeDb.addPhoneDao();
        profitLossDao = employeeDb.profitLossDao();

        recycleId = findViewById(R.id.recycleId);


    }

    private void Body() {
        list.addAll(dataDao.getAll());
        list1.addAll(addPhoneDao.getAll());
        profitLossModels.addAll(profitLossDao.getAll());

        if (profitLossModels.size() == 0) {
            recycleId.setVisibility(View.GONE);
        } else {

            soldStockAdapter = new ProfitLossAdapter(profitLossModels);
            recycleId.setHasFixedSize(true);
            recycleId.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));
            recycleId.setAdapter(soldStockAdapter);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Body();
    }
}