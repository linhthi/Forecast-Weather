package com.app.forecastweather.models.currentWeather;

import com.google.gson.annotations.SerializedName;

public class Clouds {
    @SerializedName("clouds")
    private float all;

    public Clouds() {
    }

    public Clouds(float all) {
        this.all = all;
    }

    public void setAll(float all) {
        this.all = all;
    }

    public float getAll() {
        return all;
    }
}
