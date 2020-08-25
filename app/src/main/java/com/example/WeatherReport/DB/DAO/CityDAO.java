package com.example.WeatherReport.DB.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.WeatherReport.DB.Models.CityModel;

import java.util.List;

@Dao
public interface CityDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(CityModel CityModel);

    @Update
    void update(CityModel cityModel);

    @Delete
    void delete(CityModel CityModel);

    @Query("SELECT * from CityTable")
    LiveData<List<CityModel>> getCityList();

    @Query("SELECT * from CityTable WHERE cityKey = :key")
    LiveData<List<CityModel>> getCity(String key);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCityList(List<CityModel> cityModelList);

    @Query("DELETE from CityTable WHERE cityKey = :key")
    void deleteCity(String key);

    @Query("DELETE FROM CityTable")
    void deleteAll();
}
