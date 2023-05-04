package com.example.phoneplusv3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SoldStockDao 
{
    @Insert
    void insertAll(SoldStockModel... dataEntities);

    @Delete
    void delete(SoldStockModel dataEntity);

    @Query("SELECT * FROM SoldStockModel")
    List<SoldStockModel> getAll();

    @Update
    void updateUsers(SoldStockModel dataEntity);

    @Query("SELECT * FROM SoldStockModel WHERE brandName = :firstName ")
    SoldStockModel selectUsersByBrandName(String firstName);

    @Query("SELECT * FROM SoldStockModel WHERE color = :name ")
    List<SoldStockModel> selectUserBYColor(String name);

    @Query("SELECT * FROM SoldStockModel WHERE id = :empId ")
    SoldStockModel loadUsers(int empId);


    @Query("SELECT * FROM SoldStockModel WHERE brandName = :name ")
    List<SoldStockModel> selectByBrandName(String name);

    @Query("SELECT * FROM SoldStockModel WHERE imeiNumber = :name ")
    List<SoldStockModel> selectByImei(String name);

    @Query("SELECT * FROM SoldStockModel WHERE qty = :name ")
    List<SoldStockModel> selectByQty(String name);

}
