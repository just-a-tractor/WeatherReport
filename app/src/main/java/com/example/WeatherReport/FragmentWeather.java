package com.example.WeatherReport;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WeatherReport.DB.Models.CityModel;
import com.example.WeatherReport.Entity.AsyncCityParam;
import com.example.WeatherReport.Entity.AsyncParam;
import com.example.WeatherReport.Entity.AsyncRet;
import com.example.WeatherReport.Entity.Cities.City;
import com.example.WeatherReport.Entity.CurrentConditions.CurrentConditions;
import com.example.test.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FragmentWeather extends Fragment {

    private AppActivity activity;
    private FloatingActionButton addBtn;
    private RecyclerView recyclerView;
    private List<Weather> reports = new ArrayList<>();
    private View rootView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setRetainInstance(true);
        activity = ((AppActivity) getActivity());
        rootView = inflater.inflate(R.layout.cities, container, false);

        addBtn = rootView.findViewById(R.id.AddBtn);
        addBtn.setOnClickListener(btnOnClickListener);
        recyclerView = rootView.findViewById(R.id.list);

        final ArrayList<String> keyList = new ArrayList<>();

        activity.getAsyncHelper().getDatabase().cityDAO().getCityList().observe(this, new Observer<List<CityModel>>() {
            @Override
            public void onChanged(List<CityModel> cityModels) {
                for (CityModel cm: cityModels) {
                    keyList.add(cm.getCityKey());
                }
            }
        });

        // HARDCODE:
        reports.add(new Weather("Moscow", "24℃", R.drawable.s8));
        reports.add(new Weather("Saint Petersburg", "14℃", R.drawable.s18));
        reports.add(new Weather("Ryazan", "30℃", R.drawable.s2));
        reports.add(new Weather("Balashikha", "-7°C", R.drawable.s15));

        for(String key: keyList) {
            GetCurrentConditions(key);
        }

        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(activity, reports);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private void GetCurrentConditions(String key) {

        AsyncHelper asyncHelper = activity.getAsyncHelper();
        AsyncParam<AsyncHelper> ap = new AsyncParam<>(activity, asyncHelper, false, 0, key);

        AsyncHelper.GetWeather currentConditions = new AsyncHelper.GetWeather(new AsyncHelper.OnTaskCompleteListener<AsyncRet<List<CurrentConditions>>>() {
            @Override
            public void onTaskComplete(AsyncRet<List<CurrentConditions>> ret) {
                if (ret.isError())
                    Log.d("### WEATHER ###", ret.getMessage());

                else if (ret.getObject() != null) {
                    //reports.add(new Weather(ret.getObject()));
                }
            }
        });
        currentConditions.execute(ap);
    }

    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            activity.setFragment(R.layout.city_search, true,true);
        }
    };
}