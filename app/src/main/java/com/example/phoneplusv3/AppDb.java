package com.example.phoneplusv3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {PhoneStockModel.class,SoldStockModel.class,ProfitLossModel.class},version = 1)
public abstract class AppDb extends RoomDatabase
{


    public abstract AddPhoneDao addPhoneDao();
    public abstract SoldStockDao soldStockDao();
    public abstract ProfitLossDao profitLossDao();




}
