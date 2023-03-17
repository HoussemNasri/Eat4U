package com.example.eat4u.network.db;

import android.provider.BaseColumns;

// TODO: Refactor SQL to use JEP 430: String Templates; once it is released in Java 21
public interface DatabaseContract {

    interface RestaurantEntry extends BaseColumns {
        String TABLE_NAME = "restaurant";
        String COLUMN_NAME = "name";
        String COLUMN_ADDRESS = "address";

        String SQL_CREATE_TABLE = String.format("""
                CREATE TABLE %s (
                    %s INTEGER PRIMARY KEY,
                    %s TEXT,
                    %s TEXT
                );
                """, TABLE_NAME, _ID, COLUMN_NAME, COLUMN_ADDRESS);

        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    interface UserEntry extends BaseColumns {
        String TABLE_NAME = "user";
        String COLUMN_FIRSTNAME = "firstname";
        String COLUMN_LASTNAME = "lastname";
        String COLUMN_EMAIL = "email";
        String COLUMN_PHONE = "phone";

        String SQL_CREATE_TABLE = String.format("""
                CREATE TABLE %s (
                    %s INTEGER PRIMARY KEY,
                    %s TEXT,
                    %s TEXT,
                    %s TEXT,
                    %s TEXT,
                );
                """, TABLE_NAME, _ID, COLUMN_FIRSTNAME, COLUMN_LASTNAME, COLUMN_EMAIL, COLUMN_PHONE);

        String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }

    interface RestaurantRatingEntry extends BaseColumns {
        String TABLE_NAME = "restaurant_rating";
        String COLUMN_RESTAURANT_ID = "restaurant_id";
        String COLUMN_RATER_ID = "rater_id";
        String COLUMN_SERVICE_QUALITY = "service_quality";
        String COLUMN_FOOD_QUALITY = "food_rating";
        String COLUMN_AVERAGE_PRICE = "avg_price";
        String COLUMN_STARS = "stars";

        String SQL_CREATE_TABLE = String.format("""
                        CREATE TABLE %s (
                            %s INTEGER
                            %s INTEGER
                            %s TEXT
                            %s TEXT
                            %s REAL
                            %s STRING
                            PRIMARY key (%s, %s)
                            FOREIGN KEY (%s) REFERENCES %s (%s)
                            FOREIGN KEY (%s) REFERENCES %s (%s)
                        );
                         """, TABLE_NAME, COLUMN_RESTAURANT_ID, COLUMN_RATER_ID, COLUMN_SERVICE_QUALITY, COLUMN_FOOD_QUALITY
                , COLUMN_AVERAGE_PRICE, COLUMN_STARS, COLUMN_RESTAURANT_ID, COLUMN_RATER_ID, COLUMN_RESTAURANT_ID,
                RestaurantEntry.TABLE_NAME, RestaurantEntry._ID, COLUMN_RATER_ID, UserEntry.TABLE_NAME, UserEntry._ID);
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
