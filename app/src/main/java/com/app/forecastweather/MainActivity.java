package com.app.forecastweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_dehaze_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showListCity();
            }
        });

        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frame_layout, new CityFragment());
        ft.commit();

        LocationFinder finder;
        finder = new LocationFinder(this);
        if (finder.canGetLocation()) {
        } else {
            finder.showSettingsAlert();
        }

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = new Bundle();
            bundle.putString("city_name", intent.getStringExtra("city_name"));
            bundle.putFloat("lat",intent.getFloatExtra("lat", 0));
            bundle.putFloat("lon", intent.getFloatExtra("lon", 0));
            CityFragment cityFragment = new CityFragment();
            cityFragment.setArguments(bundle);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.frame_layout, cityFragment);
            transaction.commit();


        }




    }

    private void showListCity() {
        Intent intent = new Intent(this, ListCityActivity.class);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}
