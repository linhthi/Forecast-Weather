package com.app.forecastweather;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.forecastweather.adapters.DayAdapter;
import com.app.forecastweather.adapters.HourAdapter;
import com.app.forecastweather.api.ApiClient;
import com.app.forecastweather.api.ApiWeatherService;
import com.app.forecastweather.common.Common;
import com.app.forecastweather.models.City;
import com.app.forecastweather.models.currentWeather.CurrentWeather;
import com.app.forecastweather.models.onecallWeather.OneCallWeather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CityFragment extends Fragment {

    private float latitude, longitude;


    // The onCreateView method is called when Fragment should create its View object hierarchy,
    // either dynamically or via XML layout inflation.
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup parent, @Nullable Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_city, parent, false);
    }

    // This event is triggered soon after onCreateView().
    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Setup any handles to view objects here
        final TextView cityName = (TextView) view.findViewById(R.id.city);
        final TextView celsius = (TextView) view.findViewById(R.id.txtCelsius);
        final ImageView imageViewIcon = (ImageView) view.findViewById(R.id.imageView_icon);
        final TextView description = (TextView) view.findViewById(R.id.txtDescription);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        final RecyclerView recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerViewDaily);

        // Get Location from GPS
        LocationFinder finder;
        finder = new LocationFinder(getContext());
        if (finder.canGetLocation()) {
            setLatitude((float) finder.getLatitude());
            setLongitude((float)finder.getLongitude());
        }
        //Toast.makeText(this, "lat-lng " + latitude + " — " + longitude, Toast.LENGTH_LONG).show();
        Log.d("vi tri cua ban", String.format("%f", getLongitude()));

        Intent intent = getActivity().getIntent();
//        if (intent != null) {
//            setLatitude(getArguments().getFloat("lat"));
//            setLongitude(getArguments().getFloat("lon"));
//            Log.d("truyen qua nay", getArguments().getString("city_name"));
//        }
//        setLatitude(getArguments().getFloat("lat"));
//        setLongitude(getArguments().getFloat("lon"));

        // Prepare the api to get data
        Retrofit retrofit = ApiClient.getClient();
        ApiWeatherService apiWeatherService = retrofit.create(ApiWeatherService.class);



        // Get current weather
        Call<CurrentWeather> call = apiWeatherService.
                getCurrentWeatherData(
                        String.format("%f", getLatitude()),
                        String.format("%f", getLongitude()),
                        getResources().getString(R.string.appId),
                        getResources().getString(R.string.language),
                        getResources().getString(R.string.units)
                );
        final CityDBHelper cityDBHelper = CityDBHelper.getInstance(getContext());

        // Get forecast Weather hourly and daily
        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                CurrentWeather currentWeather = response.body();
                cityName.setText(currentWeather.getName());
                celsius.setText(String.format("%d°C", Math.round(currentWeather.getMain().getTemp())));
                Picasso.with(getContext())
                        .load(Common.getImage(currentWeather.getWeathers().get(0).getIcon()))
                        .into(imageViewIcon);
                description.setText(currentWeather.getWeathers().get(0).getDescription());

                City city = new City(currentWeather.getName(), currentWeather.getSys().getCountry(), currentWeather.getCoord().getLat(), currentWeather.getCoord().getLon());
                cityDBHelper.addCity(city);
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.d("Error get current data", t.getMessage());
            }
        });


        // Get onecallweather to get forecast weather
        Call<OneCallWeather> onecall = apiWeatherService.
                getOneCallWeatherData(
                        String.format("%f", getLatitude()),
                        String.format("%f", getLongitude()),
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
                HourAdapter hourAdapter = new HourAdapter(getContext(), oneCallWeather.getHourly());
                recyclerView.setAdapter(hourAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


                // Set data for daily view
                DayAdapter dayAdapter = new DayAdapter(getContext(), oneCallWeather.getDaily());
                recyclerView1.setAdapter(dayAdapter);
                recyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


            }

            @Override
            public void onFailure(Call<OneCallWeather> call, Throwable t) {
                Log.d("oops OneCall", t.getMessage());
            }
        });
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
