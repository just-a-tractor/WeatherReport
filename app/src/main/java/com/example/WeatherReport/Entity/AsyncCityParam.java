package com.example.WeatherReport.Entity;

import android.content.Context;

import com.example.WeatherReport.DB.DB;
import com.example.WeatherReport.DB.Models.CityModel;

public class AsyncCityParam {

    private CityModel cityModel;
    private DB db;
    private Context context;
    private boolean flag;

    public AsyncCityParam(Context _context, CityModel _cityModel, DB _db, boolean _flag){
        context                 = _context;
        cityModel               = _cityModel;
        db                      = _db;
        flag                    = _flag;
    }

    public Context getContext() { return context; }
    public CityModel getCityModel() {return cityModel; }
    public DB getDb() { return db; }
    public boolean getFlag() { return flag; }
}