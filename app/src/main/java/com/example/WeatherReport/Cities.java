package com.example.WeatherReport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Cities extends AppCompatActivity implements View.OnClickListener {

    Button add;
    RecyclerView recyclerView;
    List<Weather> reports = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        recyclerView = findViewById(R.id.list);


        // HARDCODE:
        reports.add(new Weather ("Moscow · foggy", "24℃", R.drawable.foggy));
        reports.add(new Weather ("Saint Petersburg · rain", "14℃", R.drawable.rain));
        reports.add(new Weather ("Ryazan · sunny", "30℃", R.drawable.sunny));
        reports.add(new Weather ("Balashikha · storm", "-7°C", R.drawable.storm));


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        //recyclerView.addItemDecoration(new DividerItemDecoration(Cities.this,
        //        DividerItemDecoration.VERTICAL));
        DataAdapter adapter = new DataAdapter(this, reports);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.add) {
            System.out.println(6);
            Intent intent = new Intent(this, CitySearch.class);
            startActivity(intent);
        }
    }
}