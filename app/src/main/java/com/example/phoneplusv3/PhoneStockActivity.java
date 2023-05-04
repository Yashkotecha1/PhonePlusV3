package com.example.phoneplusv3;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PhoneStockActivity extends AppCompatActivity {
    RecyclerView recycleId;
    private AddPhoneDao dataDao ;
    TextView dateTime,totalPrice,totalPurchesPrice;

    Button btnAddNew;

    Spinner spinner;

    EditText edittxt_id;

    PhoneStockAdapter stockAdapter;

    long id;
    List<PhoneStockModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_stock);
        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.addPhoneDao();

        FIndViewById();

        Intent intent = getIntent();
        String name;
        name = intent.getStringExtra("brandname");


        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PhoneStockActivity.this,AddPhoneActivity.class);
                i.putExtra("brandname",name);
                startActivity(i);
            }
        });


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                id = spinner.getSelectedItemId();
                if (id == 5)
                {
                    edittxt_id.setInputType(InputType.TYPE_DATETIME_VARIATION_DATE | InputType.TYPE_CLASS_DATETIME);
                }
                else {
                    edittxt_id.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                Log.d(TAG, "onItemSelected: "+spinner.getSelectedItemId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner

        spinner.setAdapter(adapter);



    }

    private void Body() {
        String bName;
        edittxt_id.setText("");
        Intent in = getIntent();
        bName = in.getStringExtra("brandname");

        list.addAll(dataDao.selectByBrandName(bName));
        if (list.size() != 0)
        {
            recycleId.setVisibility(View.VISIBLE);

            int i=0,j=0;
            for (PhoneStockModel l :list) {

                j += l.getTotalPurchasePrice();
//                totalPurchesPrice.setText("Total Purches Price : "+j);

//                System.out.println("-==================-==-=-=-=-=-=-=-=-=-=-=-=-=-\n"+l.getBrandName()+"\n"+l.getBrandType()+"\n"+l.getImeiNumber()+l.getQty()+l.getColor());
                System.out.println("-==================-==-=-=-=-=-=-=-=-=-=-=-=-=- Image \n"+l.getImg());
//                System.out.println("-==================-==-=-=-=-=-=-=-=-=-=-=-=-=-\n"+list.get(0).getBrandName());
            }
            stockAdapter = new PhoneStockAdapter(list);
            recycleId.setHasFixedSize(true);
            recycleId.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
            recycleId.setAdapter(stockAdapter);
        }
        else {
            recycleId.setVisibility(View.GONE);
        }
    }

    private void FIndViewById()
    {

        recycleId = findViewById(R.id.recycleId);
        dateTime = findViewById(R.id.dateTime);
        btnAddNew = findViewById(R.id.btnAddNew);
        totalPurchesPrice = findViewById(R.id.totalNetPrice);
        edittxt_id = findViewById(R.id.edittxt_id);
        spinner =findViewById(R.id.spinner_id);

    }

    @Override
    protected void onStart() {
        super.onStart();
        edittxt_id.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_ENTER) {
                    list.clear();
                    if (id ==0)
                    {
                        list.addAll(dataDao.getAll());
                    }
                    else if (id == 1) {
                        list.addAll(dataDao.selectByBrandName(edittxt_id.getText().toString()));
                    } else if (id == 2) {
                        list.addAll(dataDao.selectByQty(edittxt_id.getText().toString()));
                    } else if (id == 3) {
                        list.addAll(dataDao.selectByImei(edittxt_id.getText().toString()));
                    } else if (id == 4 ) {

                        list.addAll(dataDao.selectUserBYColor(edittxt_id.getText().toString()));
                    }   else if (id == 5 ) {

                        list.addAll(dataDao.selectByDate(edittxt_id.getText().toString()));
                    }
//                    list = employeeDataDao.selectUser(edittxt_id.getText().toString());
                    Log.d(TAG, "List: "+list.size());
                    stockAdapter.notifyDataSetChanged();
                    Log.d(TAG, "onKey: "+stockAdapter.getItemCount());


                    return true;
                }
                return false;
            }
        });

        list.clear();

        if (edittxt_id.getText().toString() == null)
        {
            list.addAll(dataDao.getAll());
            stockAdapter.notifyDataSetChanged();
        }

        Body();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

    }
}