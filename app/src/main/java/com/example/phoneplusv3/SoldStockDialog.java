package com.example.phoneplusv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SoldStockDialog extends AppCompatActivity {
    EditText qty, soldPrice;
    Button btn;

    private AddPhoneDao dataDao;
    private SoldStockDao soldStockDao;
    private ProfitLossDao profitLossDao;

    DateFormater dateFormater = new DateFormater();

    ProfitLossModel profitLossModel = new ProfitLossModel();

    int id;
    private PhoneStockModel stockModel;
    private SoldStockModel soldStockModel = new SoldStockModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_stock_dialog);

        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.addPhoneDao();

        soldStockDao = employeeDb.soldStockDao();
        profitLossDao = employeeDb.profitLossDao();


        FindViewByID();
        Body();
    }

    private void Body() {
        Intent i = new Intent();

        i = getIntent();

        id = i.getIntExtra("id", 0);


        stockModel = dataDao.loadUsers(id);

        System.out.println("  " + stockModel.getBrandName() + " " + stockModel.getBrandType() + " " + stockModel.getPrice());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                soldStockModel.setBrandName(stockModel.getBrandName());
                soldStockModel.setBrandType(stockModel.getBrandType());
                soldStockModel.setImeiNumber(stockModel.getImeiNumber());
                soldStockModel.setImg(stockModel.getImg());
                soldStockModel.setMemory(stockModel.getMemory());
                soldStockModel.setMessage(stockModel.getMessage());
                soldStockModel.setPrice(stockModel.getPrice());
                soldStockModel.setSoldPrice(Integer.parseInt(soldPrice.getText().toString()));
                soldStockModel.setQty(Integer.parseInt(qty.getText().toString()));
                soldStockModel.setColor(stockModel.getColor());
                soldStockModel.setDate(dateFormater.getDate());
                soldStockModel.setRam(stockModel.getRam());
                soldStockModel.setTotalPurchasePrice(soldStockModel.getSoldPrice() * soldStockModel.getQty());

                profitLossModel.setPurchasePrice(stockModel.getPrice());
                profitLossModel.setBrandName(stockModel.getBrandName());
                profitLossModel.setTotalQty(Integer.parseInt(qty.getText().toString()));
                profitLossModel.setSellPrice(soldStockModel.getSoldPrice() * soldStockModel.getQty());
                profitLossModel.setDate(dateFormater.getDate());


                int u = stockModel.getQty() - Integer.parseInt(qty.getText().toString());

                System.out.println("qty is " + u);
                stockModel.setQty(u);

                soldStockDao.insertAll(soldStockModel);
                profitLossDao.insertAll(profitLossModel);

                if (u!=0) {
                    dataDao.updateUsers(stockModel);
                }
                else {
                    dataDao.delete(stockModel);
                }
                Intent i = new Intent(SoldStockDialog.this, SoldStockActivity.class);
                startActivity(i);

            }
        });


    }

    private void FindViewByID() {
        qty = findViewById(R.id.qty);
        soldPrice = findViewById(R.id.price);
        btn = findViewById(R.id.btn);
    }
}
