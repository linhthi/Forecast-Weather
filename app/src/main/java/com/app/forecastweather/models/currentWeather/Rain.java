package com.app.forecastweather.models.currentWeather;

import com.google.gson.annotations.SerializedName;

public class Rain {
    @SerializedName("h1")
    private float h1;

    @SerializedName("h3")
    private float h3;

    public Rain() {
    }

    public float getH1() {
        return h1;
    }

    public void setH1(float h1) {
        this.h1 = h1;
    }

    public float getH3() {
        return h3;
    }

    public void setH3(float h3) {
        this.h3 = h3;
    }
}
