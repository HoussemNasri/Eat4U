package com.example.eat4u.backend.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eat4u.backend.db.entities.RestaurantEntity;

import com.example.eat4u.backend.db.DatabaseContract.*;
import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final Logger LOGGER = Logger.getLogger(DatabaseHelper.class.getSimpleName());
    public static final String DATABASE_NAME = "eat4u.db";
    public static int DATABASE_VERSION = 13;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserEntry.SQL_CREATE_TABLE);
        db.execSQL(RestaurantEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(UserEntry.SQL_DROP_TABLE);
        db.execSQL(RestaurantEntry.SQL_DROP_TABLE);
        onCreate(db);
    }

    public void storeRestaurant(RestaurantEntity restaurant) {
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put(RestaurantEntry.COLUMN_NAME, restaurant.getRestaurantName());
        restaurantValues.put(RestaurantEntry.COLUMN_ADDRESS, restaurant.getRestaurantAddress());
        restaurantValues.put(RestaurantEntry.COLUMN_FOOD_QUALITY, restaurant.getFoodQuality().toString());
        restaurantValues.put(RestaurantEntry.COLUMN_SERVICE_QUALITY, restaurant.getServiceQuality().toString());
        restaurantValues.put(RestaurantEntry.COLUMN_AVERAGE_PRICE, restaurant.getAveragePrice());
        restaurantValues.put(RestaurantEntry.COLUMN_STARS, restaurant.getStars().toString());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(RestaurantEntry.TABLE_NAME, null, restaurantValues);
    }

}
