package com.app.forecastweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

public class AddCityActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button haiPhong, canTho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToListCityActivity();
            }
        });

        haiPhong = (Button) findViewById(R.id.haiphong);
        canTho = (Button) findViewById(R.id.cantho);

        haiPhong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHaiPhong();
            }
        });
    }

    private void backToListCityActivity() {
        Intent intent = new Intent(this, ListCityActivity.class);
        startActivity(intent);
    }

    private void addHaiPhong() {

    }
}