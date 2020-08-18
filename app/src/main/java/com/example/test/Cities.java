package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Cities extends AppCompatActivity implements View.OnClickListener {

    Button add;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cities);
        add = findViewById(R.id.add);
        add.setOnClickListener(this);
        recyclerView = findViewById(R.id.list);
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