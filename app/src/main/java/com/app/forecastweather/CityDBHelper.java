package com.app.forecastweather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.app.forecastweather.CityContract.*;
import com.app.forecastweather.models.City;

import java.util.ArrayList;

public class CityDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME= "CityWeather1.db";
    private static final int DATABASE_VERSION = 1;

    private static CityDBHelper instance;
    private SQLiteDatabase db;

    public CityDBHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    public static synchronized CityDBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new CityDBHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_CITY_TABLE = "CREATE TABLE " +
                CityTable.TABLE_NAME + " ( " +
                CityTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CityTable.COLUMN_NAME + " TEXT, " +
                CityTable.COLUMN_COUNTRY + " TEXT, " +
                CityTable.COLUMN_LAT + " REAL, " +
                CityTable.COLUMN_LONG + " REAL, " +
                "UNIQUE(" + CityTable.COLUMN_NAME + "," + CityTable.COLUMN_COUNTRY + ")"+
                ")";

        db.execSQL(SQL_CREATE_CITY_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CityTable.TABLE_NAME);
        onCreate(db);

    }

    public void addCity(City city) {
//        db = getReadableDatabase();
//        Cursor c = db.query("SELECT * FROM " + CityTable.TABLE_NAME + "WHERE" +
        db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(CityTable.COLUMN_NAME, city.getName());
        cv.put(CityTable.COLUMN_COUNTRY, city.getCountry());
        cv.put(CityTable.COLUMN_LAT, city.getLat());
        cv.put(CityTable.COLUMN_LONG, city.getLon());

        db.insert(CityTable.TABLE_NAME, null, cv);
    }

    public ArrayList<City> getCities() {
        ArrayList<City> cityArrayList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT DISTINCT * FROM " + CityTable.TABLE_NAME, null);

        if (c. moveToFirst()) {
            do {
                City city = new City();
                city.setId(c.getInt(c.getColumnIndex(CityTable._ID)));
                city.setName(c.getString(c.getColumnIndex(CityTable.COLUMN_NAME)));
                city.setCountry(c.getString(c.getColumnIndex(CityTable.COLUMN_COUNTRY)));
                city.setLat(c.getFloat(c.getColumnIndex(CityTable.COLUMN_LAT)));
                city.setLon(c.getFloat(c.getColumnIndex(CityTable.COLUMN_LONG)));
                cityArrayList.add(city);

            } while (c.moveToNext());
        }
        c.close();
        return cityArrayList;
    }
}
