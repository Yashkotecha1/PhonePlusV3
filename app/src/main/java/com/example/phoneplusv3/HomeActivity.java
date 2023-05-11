package com.example.phoneplusv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    GridView gridView;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        FindViewByID();
        Body();
    }

    private void Body()
    {

        ArrayList<BrandModel> brandModelArrayList = new ArrayList<>();

        brandModelArrayList.add(new BrandModel("Apple",R.drawable.iphonelogo));
        brandModelArrayList.add(new BrandModel("Samsung",R.drawable.samsung));
        brandModelArrayList.add(new BrandModel("Oppo",R.drawable.oppologo));
        brandModelArrayList.add(new BrandModel("Vivo",R.drawable.vivologo));
        brandModelArrayList.add(new BrandModel("One Plus",R.drawable.onepluslogo));
        brandModelArrayList.add(new BrandModel("Redmi",R.drawable.redmilogo));
        brandModelArrayList.add(new BrandModel("Google Pixel",R.drawable.googlepixellogo));
        brandModelArrayList.add(new BrandModel("Huawei",R.drawable.huaweilogo));
        brandModelArrayList.add(new BrandModel("Profit & Loss",R.drawable.profiticon));
        brandModelArrayList.add(new BrandModel("Sold Stock",R.drawable.outofstockicon));

        BradNameAdapter adapter = new BradNameAdapter(HomeActivity.this, brandModelArrayList);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 8 )
                {
                    intent = new Intent(HomeActivity.this, ProfitAndLossActivity.class);

                    startActivity(intent);
                }
                else if (i ==9)
                {
                    intent = new Intent(HomeActivity.this, SoldStockActivity.class);
                    startActivity(intent);
                }
                else {

                intent = new Intent(HomeActivity.this, PhoneStockActivity.class);
                intent.putExtra("brandname", String.valueOf(brandModelArrayList.get(i).getBrandName()));
                startActivity(intent);
            }}
        });

    }

    private void FindViewByID()
    {
        gridView = findViewById(R.id.brandName);
    }
}