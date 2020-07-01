package com.app.forecastweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.forecastweather.adapters.CityAdapter;
import com.app.forecastweather.models.City;

import java.util.ArrayList;
import java.util.List;

public class ListCityActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_city);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewCity);
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách thành phố");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });


        // Create some fake city data
        CityDBHelper cityDBHelper = CityDBHelper.getInstance(this);
        City city = new City("Ha Noi", "VietNam", 105.8342f, 21.0278f);
        City city1 = new City("Thanh Hoa", "VietNam", 105.8342f, 21.0278f);
        cityDBHelper.addCity(city);
        cityDBHelper.addCity(city1  );
        ArrayList<City> cities = cityDBHelper.getCities();
//        ArrayList<City> cities = new ArrayList<>();
//        cities.add(city);
//        cities.add(city1);
                                                                                                                                                


        cities = cityDBHelper.getCities();

        // Represent the list city
        CityAdapter cityAdapter = new CityAdapter(this, cities);
        recyclerView.setAdapter(cityAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Handle add city
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCity();
            }
        });

    }

    private void addCity() {
        Intent intent = new Intent(this, AddCityActivity.class);
        startActivity(intent);
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }
}