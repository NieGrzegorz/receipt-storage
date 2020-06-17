package com.example.paragon;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {
    private Context m_ctx;
    private static DatabaseClient m_instance;

    private AppDatabase m_appDatabase;

    public DatabaseClient(Context ctx) {
        this.m_ctx = ctx;

        m_appDatabase = Room.databaseBuilder(m_ctx, AppDatabase.class, "Receipts").build();
    }

    public static synchronized DatabaseClient getInstance(Context ctx) {
        if(m_instance == null) {
            m_instance = new DatabaseClient(ctx);
        }

        return m_instance;
    }

    public AppDatabase getAppDatabase() {
        return m_appDatabase;
    }
}
