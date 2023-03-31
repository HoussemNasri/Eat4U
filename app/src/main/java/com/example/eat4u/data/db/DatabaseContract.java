package com.example.eat4u.data.db;

import android.provider.BaseColumns;

// TODO: Refactor SQL to use JEP 430: String Templates; once it is released in Java 21
public interface DatabaseContract {

    interface RestaurantEntry extends BaseColumns {
        String TABLE_NAME = "restaurant";
        String COLUMN_NAME = "name";
        String COLUMN_ADDRESS = "address";

        String COLUMN_DESCRIPTION = "description";

        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_ADDRESS + " TEXT);";
        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    interface UserEntry extends BaseColumns {
        String TABLE_NAME = "user";
        String COLUMN_FIRSTNAME = "firstname";
        String COLUMN_LASTNAME = "lastname";
        String COLUMN_EMAIL = "email";
        String COLUMN_PHONE = "phone";

        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER PRIMARY KEY,"
                + COLUMN_FIRSTNAME + " TEXT,"
                + COLUMN_LASTNAME + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PHONE + " TEXT);";

        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    interface ReviewEntry extends BaseColumns {
        String TABLE_NAME = "restaurant_review";
        String COLUMN_RESTAURANT_ID = "restaurant_id";
        String COLUMN_RATER_ID = "rater_id";
        String COLUMN_SERVICE_QUALITY = "service_quality";
        String COLUMN_FOOD_QUALITY = "food_rating";
        String COLUMN_AVERAGE_PRICE = "avg_price";
        String COLUMN_STARS = "stars";

        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_RESTAURANT_ID + " INTEGER,"
                + COLUMN_RATER_ID + " INTEGER,"
                + COLUMN_SERVICE_QUALITY + " TEXT,"
                + COLUMN_FOOD_QUALITY + " TEXT,"
                + COLUMN_AVERAGE_PRICE + " REAL,"
                + COLUMN_STARS + " TEXT,"
                + String.format("PRIMARY KEY (%s, %s),", COLUMN_RESTAURANT_ID, COLUMN_RATER_ID)
                + String.format("FOREIGN KEY (%s) REFERENCES %s (%s)", COLUMN_RESTAURANT_ID, RestaurantEntry.TABLE_NAME, RestaurantEntry._ID)
                + String.format("FOREIGN KEY (%s) REFERENCES %s (%s)", COLUMN_RATER_ID, UserEntry.TABLE_NAME, UserEntry._ID) + ");";
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    interface PhotoEntry extends BaseColumns {
        String TABLE_NAME = "restaurant_photos";

        String COLUMN_URL = "photo_url";

        String COLUMN_FOR_RESTAURANT = "restaurant_id";

        String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + _ID + " INTEGER AUTO INCREMENT PRIMARY KEY,"
                + COLUMN_URL + " TEXT,"
                + String.format("FOREIGN KEY (%s) REFERENCES %s (%s)", COLUMN_FOR_RESTAURANT, RestaurantEntry.TABLE_NAME, RestaurantEntry._ID)
                + ");";

        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
