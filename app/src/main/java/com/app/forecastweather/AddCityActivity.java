package com.app.forecastweather;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.app.forecastweather.api.ApiClient;
import com.app.forecastweather.api.ApiWeatherService;
import com.app.forecastweather.common.Common;
import com.app.forecastweather.models.City;
import com.app.forecastweather.models.currentWeather.CurrentWeather;
import com.squareup.picasso.Picasso;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddCityActivity extends AppCompatActivity {
    Toolbar toolbar;
    Button haiPhong, canTho;
    EditText searchCity;
    ImageButton buttonSearch;
    TextView cityResult;

    City newCity;

    private String stringDefault = "Tìm kiếm tên thành phố";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        searchCity = (EditText) findViewById(R.id.search_edt);
        buttonSearch = (ImageButton) findViewById(R.id.search_button);
        cityResult = (TextView) findViewById(R.id.city_result);
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

        searchCity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (searchCity.getText().toString().equals(stringDefault)) {
                    searchCity.setText("");
                }
                return false;
            }
        });

        searchCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });

        cityResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityDBHelper cityDBHelper = CityDBHelper.getInstance(AddCityActivity.this);
                cityDBHelper.addCity(newCity);
                new SweetAlertDialog(AddCityActivity.this)
                        .setTitleText("Thêm thành công!")
                        .show();

                Intent intent = new Intent(AddCityActivity.this, ListCityActivity.class);
                startActivity(intent);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCity.clearFocus();
                InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(searchCity.getWindowToken(), 0);
                final String city = searchCity.getText().toString();

                // Prepare the api to get data
                Retrofit retrofit = ApiClient.getClient();
                ApiWeatherService apiWeatherService = retrofit.create(ApiWeatherService.class);


                // Get current weather
                Call<CurrentWeather> callCity = apiWeatherService.getCurrentWeather(city, getResources().getString(R.string.appId), getResources().getString(R.string.language), getResources().getString(R.string.units));


                // Get forecast Weather hourly and daily
                callCity.enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.code() == 200) {
                            CurrentWeather currentWeather = response.body();
                            cityResult.setVisibility(View.VISIBLE);
                            cityResult.setText(currentWeather.getName());
                            newCity = new City(currentWeather.getName(), currentWeather.getSys().getCountry(), currentWeather.getCoord().getLat(), currentWeather.getCoord().getLon());
                        } else {
                            new SweetAlertDialog(AddCityActivity.this)
                                    .setTitleText("Không tìm thấy!")
                                    .show();

                        }


                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Log.d("Error get current data", t.getMessage());
                    }
                });
            }
        });



    }

    private void backToListCityActivity() {
        Intent intent = new Intent(this, ListCityActivity.class);
        startActivity(intent);
    }

    private void addHaiPhong() {

    }

    private void performSearch() {
        searchCity.clearFocus();
        InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        in.hideSoftInputFromWindow(searchCity.getWindowToken(), 0);
        String city = searchCity.getText().toString().trim();
        new SweetAlertDialog(getBaseContext()).setTitleText(city).show();



    }

}