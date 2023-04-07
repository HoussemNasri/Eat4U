package com.example.eat4u.backend.db;

import android.provider.BaseColumns;

// TODO: Refactor SQL to use JEP 430: String Templates; once it is released in Java 21
public interface DatabaseContract {

    interface RestaurantEntry extends BaseColumns {
        String TABLE_NAME = "restaurant";
        String COLUMN_NAME = "name";
        String COLUMN_ADDRESS = "address";
        String COLUMN_SERVICE_QUALITY = "service_quality";
        String COLUMN_FOOD_QUALITY = "food_rating";
        String COLUMN_AVERAGE_PRICE = "avg_price";
        String COLUMN_STARS = "stars";

        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_SERVICE_QUALITY + " TEXT,"
                + COLUMN_FOOD_QUALITY + " TEXT,"
                + COLUMN_AVERAGE_PRICE + " REAL,"
                + COLUMN_STARS + " TEXT,"
                + COLUMN_ADDRESS + " TEXT);";
        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    interface UserEntry extends BaseColumns {
        String TABLE_NAME = "user";
        String COLUMN_FIRSTNAME = "firstname";
        String COLUMN_LASTNAME = "lastname";
        String COLUMN_EMAIL = "email";
        String COLUMN_PHONE = "phone";
        String COLUMN_AVATAR_URL = "avatar_url";

        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_AVATAR_URL + " TEXT,"
                + COLUMN_PHONE + " TEXT);";

        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
