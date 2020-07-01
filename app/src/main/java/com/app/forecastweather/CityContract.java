package com.app.forecastweather;

import android.provider.BaseColumns;

public final class CityContract {
    private CityContract() {

    }

    public static class CityTable implements BaseColumns {
        public static final String TABLE_NAME = "city";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_COUNTRY = "country";
        public static final String COLUMN_LAT = "latitude";
        public static final String COLUMN_LONG = "longitude";
    }
}
