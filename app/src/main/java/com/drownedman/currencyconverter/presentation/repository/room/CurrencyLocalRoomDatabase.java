package com.drownedman.currencyconverter.presentation.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.drownedman.currencyconverter.presentation.repository.dao.CurrencyValueDAO;
import com.drownedman.currencyconverter.presentation.repository.dto.CurrencyValueDTO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CurrencyValueDTO.class}, version = 1, exportSchema = false)
public abstract class CurrencyLocalRoomDatabase extends RoomDatabase {
    public abstract CurrencyValueDAO currencyValueDAO();

    private static volatile CurrencyLocalRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CurrencyLocalRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CurrencyLocalRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CurrencyLocalRoomDatabase.class, "exchange_rates") //db name
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
