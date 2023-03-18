package com.example.eat4u.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantRating;
import com.example.eat4u.model.User;
import com.example.eat4u.data.db.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "eat4u.db";
    public static final int DATABASE_VERSION = 2;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserEntry.SQL_CREATE_TABLE);
        db.execSQL(RestaurantEntry.SQL_CREATE_TABLE);
        db.execSQL(RestaurantRatingEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(RestaurantRatingEntry.DROP_TABLE);
        db.execSQL(UserEntry.SQL_DROP_TABLE);
        db.execSQL(RestaurantEntry.SQL_DROP_TABLE);
        onCreate(db);
    }

    public void saveRestaurant(Restaurant restaurant) {
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put(RestaurantEntry._ID, restaurant.getId());
        restaurantValues.put(RestaurantEntry.COLUMN_NAME, restaurant.getName());
        restaurantValues.put(RestaurantEntry.COLUMN_ADDRESS, restaurant.getAddress());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(RestaurantEntry.TABLE_NAME, null, restaurantValues);
    }

    public void saveUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserEntry._ID, user.getId());
        values.put(UserEntry.COLUMN_FIRSTNAME, user.getFirstname());
        values.put(UserEntry.COLUMN_LASTNAME, user.getLastname());
        values.put(UserEntry.COLUMN_EMAIL, user.getEmail());
        values.put(UserEntry.COLUMN_PHONE, user.getPhone());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(UserEntry.TABLE_NAME, null, values);
    }

    public void addRating(Long restaurantId, RestaurantRating rating) {
        ContentValues values = new ContentValues();
        values.put(RestaurantRatingEntry.COLUMN_RESTAURANT_ID, restaurantId);
        values.put(RestaurantRatingEntry.COLUMN_RATER_ID, rating.getRater().getId());
        values.put(RestaurantRatingEntry.COLUMN_FOOD_QUALITY, rating.getFoodQuality().toString());
        values.put(RestaurantRatingEntry.COLUMN_SERVICE_QUALITY, rating.getServiceQuality().toString());
        values.put(RestaurantRatingEntry.COLUMN_AVERAGE_PRICE, rating.getAveragePrice());
        values.put(RestaurantRatingEntry.COLUMN_STARS, rating.getStars().toString());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(RestaurantRatingEntry.TABLE_NAME, null, values);
    }


}
