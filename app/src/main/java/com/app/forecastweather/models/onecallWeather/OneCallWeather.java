package com.app.forecastweather.models.onecallWeather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OneCallWeather {
    @SerializedName("lat")
    private float lat;
    @SerializedName("lon")
    private float lon;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("timezone_offset")
    private int timezone_offset;
    @SerializedName("current")
    private Current current;
    @SerializedName("minutely")
    private List<Minutely> minutely;
    @SerializedName("hourly")
    private List<Hourly> hourly;
    @SerializedName("daily")
    private List<Daily> daily;

    public OneCallWeather() {
    }

    public OneCallWeather(float lat, float lon, String timezone, int timezone_offset, Current current, List<Minutely> minutely, List<Hourly> hourly, List<Daily> daily) {
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.timezone_offset = timezone_offset;
        this.current = current;
        this.minutely = minutely;
        this.hourly = hourly;
        this.daily = daily;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public int getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(int timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public List<Minutely> getMinutely() {
        return minutely;
    }

    public void setMinutely(List<Minutely> minutely) {
        this.minutely = minutely;
    }

    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }
}
