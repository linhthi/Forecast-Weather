package com.app.forecastweather.models.onecallWeather;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("1h")
    private float h1;

    public Rain() {
    }

    public Rain(float h1) {
        this.h1 = h1;
    }

    public float getH1() {
        return h1;
    }

    public void setH1(float h1) {
        this.h1 = h1;
    }
}
