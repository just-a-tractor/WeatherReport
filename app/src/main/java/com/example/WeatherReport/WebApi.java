package com.example.WeatherReport;

import com.example.WeatherReport.Entity.Cities.City;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebApi {
    @GET("/locations/{version}/search")
    Call<List<City>> getCities(@Path("version") String version, @Query("q") String city, @Query("apikey") String apiKey, @Query("language") String language);
}
