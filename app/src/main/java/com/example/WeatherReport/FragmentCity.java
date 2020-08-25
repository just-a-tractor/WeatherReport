package com.example.WeatherReport;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.WeatherReport.Adapters.AppSearchCityAdapter;
import com.example.WeatherReport.Adapters.RecyclerCityItemOnClickListener;
import com.example.WeatherReport.DB.Models.CityModel;
import com.example.WeatherReport.Entity.AsyncCityParam;
import com.example.WeatherReport.Entity.AsyncParam;
import com.example.WeatherReport.Entity.AsyncRet;
import com.example.WeatherReport.Entity.Cities.City;
import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentCity extends Fragment {
    private AppActivity activity;
    private View rootView;
    private EditText editText;
    private ProgressBar progressBar;
    private RecyclerView rvCities;
    private List<City> cityList = new ArrayList<>();
    private AppSearchCityAdapter appSearchCityAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setRetainInstance(true);
        activity = ((AppActivity) getActivity());
        rootView = inflater.inflate(R.layout.city_search, container, false);

        progressBar = rootView.findViewById(R.id.progressBar);

        editText = rootView.findViewById(R.id.editText);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }

            private void performSearch() {
                View view = activity.getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        GetCities(editText.getText().toString());
                    }
                }
            }
        });

        rvCities = rootView.findViewById(R.id.RVCities);
        appSearchCityAdapter = new AppSearchCityAdapter(activity, cityList);
        rvCities.setAdapter(appSearchCityAdapter);

        rvCities.addOnItemTouchListener(new RecyclerCityItemOnClickListener(getActivity(), new RecyclerCityItemOnClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, City objItem, int position) {
                        CityModel cityModel = new CityModel();
                        cityModel.setCityKey(objItem.Key);
                        cityModel.setCityArea(String.format("%s-%s", objItem.AdministrativeArea.LocalizedType, objItem.AdministrativeArea.LocalizedName));
                        cityModel.setCityRegion(String.format("%s/%s", objItem.Region.LocalizedName, objItem.Country.LocalizedName));

                        AsyncCityParam cp = new AsyncCityParam(activity, cityModel, activity.getAsyncHelper().getDatabase(), false);
                        new AsyncHelper.AddCityModel(new AsyncHelper.OnTaskBooleanCompleteListener<Boolean>() {
                            @Override
                            public void onTaskComplete(boolean result) {
                                if(result)
                                    activity.getSupportFragmentManager().popBackStack();
                            }
                        }).execute(cp);
                    }
                })
        );

        return rootView;
    }

    private void GetCities(String searchCity) {

        AsyncHelper asyncHelper = activity.getAsyncHelper();
        AsyncParam<AsyncHelper> ap = new AsyncParam<>(activity, asyncHelper, false, 0, searchCity);

        AsyncHelper.GetCities getCityList = new AsyncHelper.GetCities(new AsyncHelper.OnTaskCompleteListener<AsyncRet<List<City>>>() {
            @Override
            public void onTaskComplete(AsyncRet<List<City>> ret) {
                if (ret.isError())
                    Log.d("### CITY ###", ret.getMessage());

                else if (ret.getObject() != null) {
                    cityList.clear();
                    cityList.addAll(ret.getObject());
                    appSearchCityAdapter.notifyDataSetChanged();
                }

                progressBar.setVisibility(View.GONE);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        getCityList.execute(ap);
    }
}