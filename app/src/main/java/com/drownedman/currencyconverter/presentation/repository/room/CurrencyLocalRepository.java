package com.drownedman.currencyconverter.presentation.repository.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {CarTitleDTO.class}, version = 1, exportSchema = false)
public abstract class CurrencyLocalRepository extends RoomDatabase {
    public abstract CarTitleDAO carTitleDAO();
    public abstract CarPropertiesDAO carPropertiesDAO();
    public abstract ClientDAO clientDAO();

    private static volatile CarRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static CarRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CarRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CarRoomDatabase.class, "cars_1") //db name
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
