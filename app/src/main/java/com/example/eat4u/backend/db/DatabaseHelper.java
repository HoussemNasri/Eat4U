package com.example.eat4u.backend.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eat4u.backend.db.entities.RestaurantEntity;

import com.example.eat4u.backend.db.DatabaseContract.*;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.Stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final Logger LOGGER = Logger.getLogger(DatabaseHelper.class.getSimpleName());
    public static final String DATABASE_NAME = "eat4u.db";
    public static int DATABASE_VERSION = 16;

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

    /**
     * Update the restaurant if it exists and insert it if it doesn't
     * TODO: needs optimization!
     */
    public void upsertRestaurant(RestaurantEntity restaurant) {
        if (getRestaurantById(restaurant.geRestaurantId()).isPresent()) {
            deleteRestaurantWithId(restaurant.geRestaurantId());
        }
        storeRestaurant(restaurant);
    }

    public Optional<RestaurantEntity> getRestaurantById(Long restaurantId) {
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1",
                        RestaurantEntry.TABLE_NAME, RestaurantEntry._ID),
                new String[]{restaurantId.toString()});

        boolean isEmpty = !cursor.moveToFirst();
        if (isEmpty) {
            return Optional.empty();
        }

        return Optional.of(readRestaurantRow(cursor));
    }

    public void deleteRestaurantWithId(Long restaurantId) {
        if (restaurantId == null) {
            throw new IllegalArgumentException("Cannot delete restaurant with a null id");
        }
        getWritableDatabase().delete(RestaurantEntry.TABLE_NAME, RestaurantEntry._ID + "= ?",
                new String[]{restaurantId.toString()});
    }

    public List<RestaurantEntity> getAllRestaurants() {
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s", RestaurantEntry.TABLE_NAME), null);

        boolean isTableEmpty = !cursor.moveToFirst();
        if (isTableEmpty) {
            return Collections.emptyList();
        }

        List<RestaurantEntity> result = new ArrayList<>();
        result.add(readRestaurantRow(cursor));

        while (cursor.moveToNext()) {
            result.add(readRestaurantRow(cursor));
        }

        return result;
    }

    private RestaurantEntity readRestaurantRow(Cursor cursor) {
        return new RestaurantEntity(
                cursor.getLong(cursor.getColumnIndexOrThrow(RestaurantEntry._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_ADDRESS)),
                Quality.parse(cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_FOOD_QUALITY))),
                Quality.parse(cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_SERVICE_QUALITY))),
                Stars.parse(cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_STARS))),
                cursor.getDouble(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_AVERAGE_PRICE))
        );
    }

}
