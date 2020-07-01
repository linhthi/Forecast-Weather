package com.app.forecastweather.models.onecallWeather;

import com.app.forecastweather.models.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Daily {
    @SerializedName("dt")
    private long dt;
    @SerializedName("sunrise")
    private long sunrise;
    @SerializedName("sunset")
    private long sunset;
    @SerializedName("temp")
    private Temp temp;
    @SerializedName("feel_like")
    private FeelLike feel_like;
    @SerializedName("pressure")
    private int pressure;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("dew_point")
    private float dew_point;
    @SerializedName("wind_speed")
    private float wind_speed;
    @SerializedName("wind_gust")
    private float wind_gust;
    @SerializedName("wind_deg")
    private float wind_deg;
    @SerializedName("clouds")
    private float clouds;
    @SerializedName("uvi")
    private float uvi;
    @SerializedName("visibility")
    private float visibility;
    @SerializedName("rain")
    private float rain;
    @SerializedName("snow")
    private float snow;
    @SerializedName("weather")
    private List<Weather> weathers;

    public Daily() {
    }

    public Daily(long dt, long sunrise, long sunset, Temp temp, FeelLike feel_like, int pressure, int humidity, float dew_point, float wind_speed, float wind_gust, float wind_deg, float clouds, float uvi, float visibility, float rain, float snow, List<Weather> weathers) {
        this.dt = dt;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.feel_like = feel_like;
        this.pressure = pressure;
        this.humidity = humidity;
        this.dew_point = dew_point;
        this.wind_speed = wind_speed;
        this.wind_gust = wind_gust;
        this.wind_deg = wind_deg;
        this.clouds = clouds;
        this.uvi = uvi;
        this.visibility = visibility;
        this.rain = rain;
        this.snow = snow;
        this.weathers = weathers;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public Temp getTemp() {
        return temp;
    }

    public void setTemp(Temp temp) {
        this.temp = temp;
    }

    public FeelLike getFeel_like() {
        return feel_like;
    }

    public void setFeel_like(FeelLike feel_like) {
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

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getWind_gust() {
        return wind_gust;
    }

    public void setWind_gust(float wind_gust) {
        this.wind_gust = wind_gust;
    }

    public float getWind_deg() {
        return wind_deg;
    }

    public void setWind_deg(float wind_deg) {
        this.wind_deg = wind_deg;
    }

    public float getClouds() {
        return clouds;
    }

    public void setClouds(float clouds) {
        this.clouds = clouds;
    }

    public float getUvi() {
        return uvi;
    }

    public void setUvi(float uvi) {
        this.uvi = uvi;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(float visibility) {
        this.visibility = visibility;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public float getSnow() {
        return snow;
    }

    public void setSnow(float snow) {
        this.snow = snow;
    }

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }
}
