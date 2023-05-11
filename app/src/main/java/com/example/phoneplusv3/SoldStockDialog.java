package com.example.phoneplusv3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class SoldStockDialog extends AppCompatActivity {


    EditText txt_brandName, txt_memory, txt_brandType, txt_imeiNumber, txt_qtyname, txt_color, txt_message, txt_price, txt_ram;
    ImageView cameraBtn;
    TextView selce;

    TextInputLayout ram;
    Button submitinq;
    private AddPhoneDao dataDao;
    private SoldStockDao soldStockDao;
    private ProfitLossDao profitLossDao;

    DateFormater dateFormater = new DateFormater();
    ProfitLossModel profitLossModel = new ProfitLossModel();
    int id;

    String brandName;
    private PhoneStockModel stockModel;
    private SoldStockModel soldStockModel = new SoldStockModel();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sold_stock_dialog);

        mDataBase();

        brandName = stockModel.getBrandName();
        FindViewByID();
        if (brandName.equals("Iphone")) {
            txt_brandName.setText("Apple");
            txt_brandType.setText(""+stockModel.getBrandName()+"   "+stockModel.getBrandType());
            ram.setVisibility(View.GONE);
            txt_ram.setVisibility(View.GONE);
            selce.setVisibility(View.GONE);

        } else {
            txt_brandName.setText(""+stockModel.getBrandName());
            txt_brandType.setText(""+stockModel.getBrandType());
            txt_ram.setVisibility(View.VISIBLE);
            selce.setVisibility(View.VISIBLE);
            txt_ram.setText(""+stockModel.getRam());
        }
        setTextData();

        Body();
    }

    private void mDataBase()
    {
        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.addPhoneDao();

        soldStockDao = employeeDb.soldStockDao();
        profitLossDao = employeeDb.profitLossDao();
        Intent i = new Intent();

        i = getIntent();
        id = i.getIntExtra("id", 0);
        stockModel = dataDao.loadUsers(id);

    }

    private void Body() {

        System.out.println("  " + stockModel.getBrandName() + " " + stockModel.getBrandType() + " " + stockModel.getPrice());

        submitinq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                soldStockModel.setBrandName(stockModel.getBrandName());
                soldStockModel.setBrandType(stockModel.getBrandType());
                soldStockModel.setImeiNumber(stockModel.getImeiNumber());
                soldStockModel.setImg(stockModel.getImg());
                soldStockModel.setMemory(stockModel.getMemory());
                soldStockModel.setMessage(stockModel.getMessage());
                soldStockModel.setPrice(stockModel.getTotalPurchasePrice());
                soldStockModel.setSoldPrice(Integer.parseInt(txt_price.getText().toString()));
                soldStockModel.setQty(Integer.parseInt(txt_qtyname.getText().toString()));
                soldStockModel.setColor(stockModel.getColor());
                soldStockModel.setDate(dateFormater.getDate());
                soldStockModel.setRam(stockModel.getRam());
                soldStockModel.setTotalPurchasePrice(soldStockModel.getSoldPrice() * soldStockModel.getQty());

                profitLossModel.setPurchasePrice(stockModel.getPrice()*stockModel.getQty());
                profitLossModel.setBrandName(stockModel.getBrandName());
                profitLossModel.setTotalQty(Integer.parseInt(txt_qtyname.getText().toString()));
                profitLossModel.setSellPrice(soldStockModel.getSoldPrice() * soldStockModel.getQty());
                profitLossModel.setDate(dateFormater.getDate());


                int u = stockModel.getQty() - Integer.parseInt(txt_qtyname.getText().toString());

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
    @SuppressLint("SetTextI18n")
    private void setTextData()
    {
        cameraBtn.setBackgroundResource(R.color.white);
        cameraBtn.setImageDrawable(Drawable.createFromPath(String.valueOf(stockModel.getImg())));
        txt_imeiNumber.setText(""+stockModel.getImeiNumber());
        txt_qtyname.setText(""+stockModel.getQty());
        txt_message.setText(""+stockModel.getMessage());
        txt_memory.setText(""+stockModel.getMemory());
        txt_price.setText(""+stockModel.getPrice());
        txt_color.setText(""+stockModel.getColor());

    }
    private void FindViewByID() {
        txt_brandName = findViewById(R.id.txt_brandName);
        txt_brandType = findViewById(R.id.txt_brandType);
        txt_imeiNumber = findViewById(R.id.txt_imeiNumber);
        txt_qtyname = findViewById(R.id.txt_qtyname);
        txt_message = findViewById(R.id.txt_message);
        cameraBtn = findViewById(R.id.cameraBtn);
        txt_memory = findViewById(R.id.txt_memory);
        txt_price = findViewById(R.id.txt_price);
        submitinq = findViewById(R.id.submitinq);
        txt_ram = findViewById(R.id.txt_ram);
        txt_color = findViewById(R.id.txt_color);
        selce = findViewById(R.id.selce);
        ram = findViewById(R.id.ram);
    }
}
