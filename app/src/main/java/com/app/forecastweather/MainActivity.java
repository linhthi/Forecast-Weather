package com.app.forecastweather;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.forecastweather.adapters.DayAdapter;
import com.app.forecastweather.adapters.HourAdapter;
import com.app.forecastweather.api.ApiClient;
import com.app.forecastweather.api.ApiWeatherService;
import com.app.forecastweather.common.Common;
import com.app.forecastweather.models.City;
import com.app.forecastweather.models.currentWeather.CurrentWeather;
import com.app.forecastweather.models.currentWeather.Main;
import com.app.forecastweather.models.onecallWeather.OneCallWeather;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView cityName, celsius, description;
    ImageView imageViewIcon;
    RecyclerView recyclerView, recyclerView1;

    private static float latitude, longitude;
    CityDBHelper cityDBHelper;
    String city = null;


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

        // Setup any handles to view objects here
        cityName = (TextView) findViewById(R.id.city);
        celsius = (TextView) findViewById(R.id.txtCelsius);
        imageViewIcon = (ImageView) findViewById(R.id.imageView_icon);
        description = (TextView) findViewById(R.id.txtDescription);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView1 = (RecyclerView) findViewById(R.id.recyclerViewDaily);



        // Get Location from GPS
        LocationFinder finder;
        finder = new LocationFinder(this);
        if (finder.canGetLocation()) {
            setLatitude((float) finder.getLatitude());
            setLongitude((float)finder.getLongitude());
        } else {
            finder.showSettingsAlert();
        }


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            setLatitude(bundle.getFloat("lat"));
            setLongitude(bundle.getFloat("lon"));
            city = bundle.getString("city_name");
//            Toast.makeText(this, "lat-lng" + latitude + "-" + longitude, Toast.LENGTH_LONG).show();
        }


        callData(this.getLatitude(), this.getLongitude());


    }

    private void showListCity() {
        Intent intent = new Intent(this, ListCityActivity.class);
        startActivity(intent);
    }

    private void callData(float latitude, float longitude) {

        // Prepare the api to get data
        Retrofit retrofit = ApiClient.getClient();
        ApiWeatherService apiWeatherService = retrofit.create(ApiWeatherService.class);


        // Get current weather
        Call<CurrentWeather> call = apiWeatherService.getCurrentWeatherData(String.format("%f", latitude), String.format("%f", longitude), getResources().getString(R.string.appId), getResources().getString(R.string.language), getResources().getString(R.string.units));

        cityDBHelper = CityDBHelper.getInstance(this);

        // Get forecast Weather hourly and daily
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                CurrentWeather currentWeather = response.body();
                cityName.setText(city);
                if (city == null) {
                    cityName.setText(currentWeather.getName());
                }
                celsius.setText(String.format("%d°C", Math.round(currentWeather.getMain().getTemp())));
                Picasso.with(MainActivity.this)
                        .load(Common.getImage(currentWeather.getWeathers().get(0).getIcon()))
                        .into(imageViewIcon);
                description.setText(currentWeather.getWeathers().get(0).getDescription());
//                Toast.makeText(getBaseContext(), "lat-lng" + latitude + "-" + longitude + "-" + currentWeather.getName(), Toast.LENGTH_LONG).show();


                try {
                    City city = new City(currentWeather.getName(), currentWeather.getSys().getCountry(), currentWeather.getCoord().getLat(), currentWeather.getCoord().getLon());
                    cityDBHelper.addCity(city);
                } catch (Exception e) {
                    Log.e("Error", e.getMessage());

                }

            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.d("Error get current data", t.getMessage());
            }
        });



        // Get onecallweather to get forecast weather
        Call<OneCallWeather> onecall = apiWeatherService.getOneCallWeatherData(
                        String.format("%f", latitude),
                        String.format("%f", longitude),
                        "",
                        "vi",
                        "metric",
                        "8062a67002e821d38ee7093eb0b92c99"
                );
        onecall.enqueue(new Callback<OneCallWeather>() {
            @Override
            public void onResponse(Call<OneCallWeather> call, Response<OneCallWeather> response) {

                // Get the data onecall
                OneCallWeather oneCallWeather = response.body();

                // Set data for hourly view
                HourAdapter hourAdapter = new HourAdapter(MainActivity.this, oneCallWeather.getHourly());
                recyclerView.setAdapter(hourAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));


                // Set data for daily view
                DayAdapter dayAdapter = new DayAdapter(MainActivity.this, oneCallWeather.getDaily());
                recyclerView1.setAdapter(dayAdapter);
                recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));


            }

            @Override
            public void onFailure(Call<OneCallWeather> call, Throwable t) {
                Log.d("oops OneCall", t.getMessage());
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
