package com.example.WeatherReport;

import com.example.WeatherReport.Entity.Cities.City;
import com.example.WeatherReport.Entity.CurrentConditions.CurrentConditions;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebApi {
    @GET("/locations/{version}/search")
    Call<List<City>> getCities(@Path("version") String version, @Query("q") String city, @Query("apikey") String apiKey, @Query("language") String language);

    @GET("/currentconditions/{version}/{cityKey}") // http://api.accuweather.com/currentconditions/v1/294021.json?apikey=296894b065a2471290a6750ff2dbd619&language=ru&details=true
    Call<List<CurrentConditions>> getCurrentConditions(@Path("version") String version, @Path("cityKey") String cityKey, @Query("apikey") String apiKey, @Query("language") String language, @Query("details") boolean details);
}
