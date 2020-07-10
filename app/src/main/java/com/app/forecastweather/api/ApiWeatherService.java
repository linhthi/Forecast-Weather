package com.app.forecastweather.api;

import com.app.forecastweather.models.currentWeather.CurrentWeather;
import com.app.forecastweather.models.onecallWeather.OneCallWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiWeatherService {

    /**
     * Get current  weather of city
     * @param lat
     * @param lon
     * @param appId
     * @param lang
     * @param units
     * @return instance of {@link CurrentWeather}
     */
    @GET("weather")
    Call<CurrentWeather> getCurrentWeatherData(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("appid") String appId,
            @Query("lang") String lang,
            @Query("units") String units
    );

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(
            @Query("q") String cityName,
            @Query("appid") String appId,
            @Query("lang") String lang,
            @Query("units") String units
    );

    /**
     * Get one call weather data of city
     * @param lat
     * @param lon
     * @param exclude
     * @param language
     * @param units
     * @param appId
     * @return instance of {@link OneCallWeather}
     */
    @GET("onecall")
    Call<OneCallWeather> getOneCallWeatherData(
            @Query("lat") String lat,
            @Query("lon") String lon,
            @Query("exclude") String exclude,
            @Query("lang") String language,
            @Query("units") String units,
            @Query("appid") String appId
    );
}
