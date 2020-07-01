package com.app.forecastweather.models.onecallWeather;

import com.google.gson.annotations.SerializedName;

public class Minutely {
    @SerializedName("dt")
    private long dt;

    @SerializedName("precipitation")
    private float precipitation;

    public Minutely() {
    }

    public Minutely(long dt, float precipitation) {
        this.dt = dt;
        this.precipitation = precipitation;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(float precipitation) {
        this.precipitation = precipitation;
    }
}
