package com.example.phoneplusv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProfitAndLossActivity extends AppCompatActivity {
    RecyclerView recycleId;
    private SoldStockDao dataDao ;
    private AddPhoneDao addPhoneDao ;
    private ProfitLossDao profitLossDao ;
    ProfitLossAdapter soldStockAdapter;

    int profitLoss;

    PhoneStockModel phoneStockModel = new PhoneStockModel();
    List<SoldStockModel> list = new ArrayList<>();
    List<PhoneStockModel> list1 = new ArrayList<>();
    List<ProfitLossModel> profitLossModels = new ArrayList<>();


    ProfitLossModel profitLossModel = new ProfitLossModel();

    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit_and_loss);
        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.soldStockDao();
        addPhoneDao = employeeDb.addPhoneDao();
        profitLossDao = employeeDb.profitLossDao();

//        recycleId = findViewById(R.id.recycleId);


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
//        Body();
        chart();
    }

    private void chart()
    {
        barChart = findViewById(R.id.idBarChart);

        // calling method to get bar entries.
        getBarEntries();


        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Profit Loss");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        if (profitLoss>phoneStockModel.getTotalPurchasePrice()) {
            barDataSet.setColors(Color.RED);
        }
        else {
            barDataSet.setColors(Color.GREEN);
        }
        // setting text color.
//        barDataSet.setValueTypeface(Typeface.createFromFile(phoneStockModel.getBrandName()));
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);
    }

    private void getBarEntries()
    {
        list.addAll(dataDao.getAll());
        list1.addAll(addPhoneDao.getAll());
        profitLossModels.addAll(profitLossDao.getAll());
        barEntriesArrayList = new ArrayList<>();


        for (ProfitLossModel p :profitLossModels )
        {
            profitLoss = p.getSellPrice()-p.getPurchasePrice();
            System.out.println(p.getSellPrice()-p.getPurchasePrice() + "--------------------------"+p.getId());
            barEntriesArrayList.add(new BarEntry(p.getId(), p.getSellPrice()-p.getPurchasePrice()));

        }

    }
}