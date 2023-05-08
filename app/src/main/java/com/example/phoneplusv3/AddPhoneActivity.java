package com.example.phoneplusv3;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddPhoneActivity extends AppCompatActivity {
    EditText txt_brandName, txt_memory, txt_brandType, txt_imeiNumber, txt_qtyname, txt_color, txt_message, txt_price, txt_ram;
    ImageView cameraBtn;

    TextInputLayout ram;

    String imageName;

    TextView selce;
    private AddPhoneDao dataDao;

    String imageUriLoader;


    Button submitinq;

    PhoneStockModel phoneStockModel1 = new PhoneStockModel();

    ProfitLossModel profitLossModel = new ProfitLossModel();


    private static final int pic_id = 123;

    DateFormater currentDate = new DateFormater();

    String name, color, type, price, imei, memory, message, qty;
    int ram1;

    int totalPrice = 0;

    private Intent intent;
    String brandName;

    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone);
        AppDb employeeDb = Room.databaseBuilder(getApplicationContext(),
                AppDb.class, "database-name-stock").allowMainThreadQueries().build();

        dataDao = employeeDb.addPhoneDao();
        intent = getIntent();

        brandName = intent.getStringExtra("brandname");


        FindViewById();
        if (brandName.equals("Iphone")) {
            txt_brandName.setText("Apple");
            ram.setVisibility(View.GONE);
            txt_ram.setVisibility(View.GONE);
            selce.setVisibility(View.GONE);

            ram1 = 0;
        } else {
            txt_ram.setVisibility(View.VISIBLE);
            selce.setVisibility(View.VISIBLE);
        }
        Body();
    }

    private void Body() {

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Start the activity with camera_intent, and request pic id


                startActivityForResult(camera_intent, pic_id);
            }
        });


        txt_brandName.setText(brandName);

        submitinq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = brandName;
                type = txt_brandType.getText().toString();
                imei = txt_imeiNumber.getText().toString();
                qty = txt_qtyname.getText().toString();
                memory = txt_memory.getText().toString();
                message = txt_message.getText().toString();
                price = txt_price.getText().toString();
                color = txt_color.getText().toString();


                Validation();


            }
        });


    }

    private void phoneModel() {


        totalPrice = Integer.parseInt(price) * Integer.parseInt(qty);
        System.out.println("Date" + currentDate.getDate() + "\n Total price" + totalPrice);


        phoneStockModel1.setBrandName(name);
        phoneStockModel1.setBrandType(type);
        phoneStockModel1.setImeiNumber(imei);
        phoneStockModel1.setQty(Integer.parseInt(qty));
        phoneStockModel1.setMemory(Integer.parseInt(memory));
        phoneStockModel1.setMessage(message);
        phoneStockModel1.setPrice(Integer.parseInt(price));
        phoneStockModel1.setColor(color);
        phoneStockModel1.setRam(ram1);
        phoneStockModel1.setDate(currentDate.getDate());
        phoneStockModel1.setTotalPurchasePrice(totalPrice);


        dataDao.insertAll(phoneStockModel1);

        finish();
    }

    private void Validation() {
        if (TextUtils.isEmpty(type)) {
            txt_brandType.setError("Type is required!");
        } else if (TextUtils.isEmpty(imei)) {
            txt_imeiNumber.setError("Imei is required!");
        } else if (TextUtils.isEmpty(qty)) {
            txt_qtyname.setError("Qty is required!");
        } else if (TextUtils.isEmpty(memory)) {
            txt_memory.setError("Memory is required!");
        } else if (TextUtils.isEmpty(price)) {
            txt_price.setError("Price is required!");
        } else if (TextUtils.isEmpty(color)) {
            txt_color.setError("Color is required!");
        }

        phoneModel();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == pic_id) {

            Date currentDate = new Date();
            String dateFormat = "dd-MM-yyyyhh:mm:ss"; // Specify your own date format
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
            String currentTimeString = formatter.format(currentDate);


            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ContextWrapper cw = new ContextWrapper(getApplicationContext());

            imageName = currentTimeString;

            Log.d(TAG, "phoneModel: " + imageName);

            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            file = new File(directory, imageName+".jpg");

            if (!file.exists()) {
                Log.d("path", file.toString());
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                } catch (java.io.IOException e) {
                    e.printStackTrace();
                }

                cameraBtn.setBackgroundResource(R.color.white);
                cameraBtn.setImageDrawable(Drawable.createFromPath(file.toString()));
                phoneStockModel1.setImg(file.toString());


            }
        }
    }

    @SuppressLint("WrongViewCast")
    private void FindViewById() {
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