package com.example.eat4u.network.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantRating;
import com.example.eat4u.model.User;
import com.example.eat4u.network.db.DatabaseContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "eat4u.db";
    public static final int DATABASE_VERSION = 1;

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
        SQLiteDatabase db = getWritableDatabase();
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put(RestaurantEntry._ID, restaurant.getId());
        restaurantValues.put(RestaurantEntry.COLUMN_NAME, restaurant.getName());
        restaurantValues.put(RestaurantEntry.COLUMN_ADDRESS, restaurant.getAddress());

        Long newRestaurantId = db.insert(RestaurantEntry.TABLE_NAME, null, restaurantValues);
    }

    public void saveUser(User user) {

    }

    public void saveRestaurantRating(Long restaurantId, RestaurantRating rating) {

    }


}
