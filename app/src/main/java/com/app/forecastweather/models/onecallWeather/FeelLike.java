package com.app.forecastweather.models.onecallWeather;

import com.google.gson.annotations.SerializedName;

public class FeelLike {
    @SerializedName("day")
    private float day;
    @SerializedName("night")
    private float night;
    @SerializedName("eve")
    private float eve;
    @SerializedName("morn")
    private float morn;

    public FeelLike() {
    }

    public FeelLike(float day, float night, float eve, float morn) {
        this.day = day;
        this.night = night;
        this.eve = eve;
        this.morn = morn;
    }

    public float getDay() {
        return day;
    }

    public void setDay(float day) {
        this.day = day;
    }

    public float getNight() {
        return night;
    }

    public void setNight(float night) {
        this.night = night;
    }

    public float getEve() {
        return eve;
    }

    public void setEve(float eve) {
        this.eve = eve;
    }

    public float getMorn() {
        return morn;
    }

    public void setMorn(float morn) {
        this.morn = morn;
    }
}
