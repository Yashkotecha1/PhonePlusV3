package com.example.phoneplusv3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AddPhoneDao {
    @Insert
    void insertAll(PhoneStockModel... dataEntities);

    @Delete
    void delete(PhoneStockModel dataEntity);

    @Query("SELECT * FROM PhoneStockModel")
    List<PhoneStockModel> getAll();

    @Update
    void updateUsers(PhoneStockModel dataEntity);

    @Query("SELECT * FROM PhoneStockModel WHERE brandName = :firstName ")
    PhoneStockModel selectUsersByBrandName(String firstName);

    @Query("SELECT * FROM PhoneStockModel WHERE color = :name ")
    List<PhoneStockModel> selectUserBYColor(String name);

    @Query("SELECT * FROM PhoneStockModel WHERE id = :empId ")
    PhoneStockModel loadUsers(int empId);

    @Query("SELECT * FROM PhoneStockModel WHERE brandName = :name ")
    List<PhoneStockModel> selectByBrandName(String name);

    @Query("SELECT * FROM PhoneStockModel WHERE imeiNumber = :name ")
    List<PhoneStockModel> selectByImei(String name);

    @Query("SELECT * FROM PhoneStockModel WHERE qty = :name ")
    List<PhoneStockModel> selectByQty(String name);

    @Query("SELECT * FROM PhoneStockModel WHERE date = :name ")
    List<PhoneStockModel> selectByDate(String name);

}
