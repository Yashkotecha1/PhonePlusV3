package com.example.phoneplusv3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProfitLossDao {
    @Insert
    void insertAll(ProfitLossModel... dataEntities);

    @Delete
    void delete(ProfitLossModel dataEntity);

    @Query("SELECT * FROM ProfitLossModel")
    List<ProfitLossModel> getAll();

    @Update
    void updateUsers(ProfitLossModel dataEntity);

    @Query("SELECT * FROM ProfitLossModel WHERE brandName = :firstName ")
    ProfitLossModel selectUsersByBrandName(String firstName);


    @Query("SELECT * FROM ProfitLossModel WHERE id = :empId ")
    ProfitLossModel loadUsers(int empId);


    @Query("SELECT * FROM ProfitLossModel WHERE brandName = :name ")
    List<ProfitLossModel> selectByBrandName(String name);

    @Query("SELECT * FROM ProfitLossModel WHERE  Date = :name ")
    List<ProfitLossModel> selectByDate(String name);

}
