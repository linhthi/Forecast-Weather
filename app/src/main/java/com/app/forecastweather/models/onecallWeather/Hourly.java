package com.app.forecastweather.models.onecallWeather;

import com.app.forecastweather.models.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hourly {
    @SerializedName("dt")
    private long dt;

    @SerializedName("temp")
    private float temp;

    @SerializedName("feel_like")
    private float feel_like;

    @SerializedName("pressure")
    private int pressure;

    @SerializedName("humidity")
    private int humidity;

    @SerializedName("dew_point")
    private float dew_point;

    @SerializedName("clouds")
    private int clouds;

    @SerializedName("visibility")
    private int visibility;

    @SerializedName("wind_speed")
    private float wind_speed;

    @SerializedName("wind_deg")
    private int wind_deg;

    @SerializedName("weather")
    private List<Weather> weathers;

    @SerializedName("rain")
    private Rain rain;

    public Hourly() {
    }

    public Hourly(long dt, float temp, float feel_like, int pressure, int humidity, float dew_point, int clouds, int visibility, float wind_speed, int wind_deg, List<Weather> weathers, Rain rain) {
        this.dt = dt;
        this.temp = temp;
        this.feel_like = feel_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dew_point = dew_point;
        this.clouds = clouds;
        this.visibility = visibility;
        this.wind_speed = wind_speed;
        this.wind_deg = wind_deg;
        this.weathers = weathers;
        this.rain = rain;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(float feel_like) {
        this.feel_like = feel_like;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public float getDew_point() {
        return dew_point;
    }

    public void setDew_point(float dew_point) {
        this.dew_point = dew_point;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public int getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(int wind_deg) {
        this.wind_deg = wind_deg;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }
}
