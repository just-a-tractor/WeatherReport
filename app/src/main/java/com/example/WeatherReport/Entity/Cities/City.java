
package com.example.WeatherReport.Entity.Cities;

import java.util.List;

public class City {

    public Integer Version;
    public String Key;
    public String Type;
    public Integer Rank;
    public String LocalizedName;
    public String EnglishName;
    public String PrimaryPostalCode;
    public Region Region;
    public Country Country;
    public AdministrativeArea AdministrativeArea;
    public TimeZone TimeZone;
    public GeoPosition GeoPosition;
    public Boolean IsAlias;
    public List<Object> SupplementalAdminAreas = null;
    public List<Object> DataSets = null;

}
