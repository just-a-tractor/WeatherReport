package com.example.WeatherReport.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WeatherReport.Entity.Cities.City;
import com.example.test.R;

import java.util.List;

public class AppSearchCityAdapter extends RecyclerView.Adapter<AppSearchCityAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<City> cityList;

    public AppSearchCityAdapter(Context context, List<City> cities) {
        this.cityList = cities;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public AppSearchCityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.app_layout_city_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AppSearchCityAdapter.ViewHolder holder, int position) {
        City city = cityList.get(position);

        holder.cityView.setText(city.LocalizedName);
        holder.areaView.setText(String.format("%s-%s", city.AdministrativeArea.LocalizedType, city.AdministrativeArea.LocalizedName));
        holder.regionView.setText(String.format("%s/%s", city.Region.LocalizedName, city.Country.LocalizedName));
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public City getItem(int position) {
        return cityList.get(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView cityView, regionView, areaView;

        ViewHolder(View view) {
            super(view);
            regionView = view.findViewById(R.id.CityRegionInfoText);
            cityView = view.findViewById(R.id.CityInfoText);
            areaView = view.findViewById(R.id.CityAreaInfoText);
        }
    }
}
