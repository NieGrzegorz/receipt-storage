package com.example.paragon;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Receipt.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ReceiptDao receiptDao();
}
