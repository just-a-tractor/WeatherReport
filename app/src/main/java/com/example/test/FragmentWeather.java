package com.example.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

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

        // HARDCODE:
        reports.add(new Weather("Moscow", "24℃", R.drawable.foggy));
        reports.add(new Weather("Saint Petersburg", "14℃", R.drawable.rain));
        reports.add(new Weather("Ryazan", "30℃", R.drawable.sunny));
        reports.add(new Weather("Balashikha", "-7°C", R.drawable.storm));

        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        DataAdapter adapter = new DataAdapter(activity, reports);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    private View.OnClickListener btnOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            activity.setFragment(R.layout.city_search, true,true);
        }
    };
}