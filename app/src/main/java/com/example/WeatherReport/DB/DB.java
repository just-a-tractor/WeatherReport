package com.example.WeatherReport.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.WeatherReport.DB.DAO.CityDAO;
import com.example.WeatherReport.DB.Models.CityModel;
import com.example.WeatherReport.Utils.Utils;


@Database(entities = {
        CityModel.class
}, version = 1, exportSchema = false)

//@TypeConverters({DateConverter.class})

public abstract class DB extends RoomDatabase {

    public abstract CityDAO cityDAO();

    private static DB INSTANCE;

    public static DB getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (DB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DB.class, Utils.DB_NAME_KEY)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

