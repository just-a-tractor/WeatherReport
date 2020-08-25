package com.example.WeatherReport.DB.Models;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "CityTable")
public class CityModel {

    @PrimaryKey
    @ColumnInfo(name = "cityKey")
    @NonNull
    private String cityKey;

    @ColumnInfo(name = "cityArea")
    private String cityArea;

    @ColumnInfo(name = "cityRegion")
    private String cityRegion;


    public String getCityKey() {
        return cityKey;
    }

    public void setCityKey(String _Key) {
        this.cityKey = _Key;
    }

    public String getCityArea() {
        return cityArea;
    }

    public void setCityArea(String _Area) {
        this.cityArea = _Area;
    }

    public String getCityRegion() {
        return cityRegion;
    }

    public void setCityRegion(String _Region) {
        this.cityRegion = _Region;
    }
}
