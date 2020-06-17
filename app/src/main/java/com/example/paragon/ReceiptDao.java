package com.example.paragon;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.paragon.Receipt;

import java.util.List;

@Dao

public interface ReceiptDao {

    @Query("SELECT * FROM receipt")
    List<Receipt> getAll();

    @Insert
    void insert(Receipt receipt);

    @Delete
    void delete(Receipt receipt);

    @Update
    void update(Receipt receipt);
}
