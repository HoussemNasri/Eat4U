package com.example.eat4u.network.db;

import android.provider.BaseColumns;

public interface DatabaseContract {

    interface RestaurantEntry extends BaseColumns {
        String TABLE_NAME = "restaurant";
        String COLUMN_NAME = "name";
        String COLUMN_ADDRESS = "address";
    }

    interface UserEntry extends BaseColumns {
        String TABLE_NAME = "user";
        String COLUMN_FIRSTNAME = "firstname";
        String COLUMN_LASTNAME = "lastname";
        String COLUMN_EMAIL = "email";
        String COLUMN_PHONE = "phone";
    }

    interface RestaurantRatingEntry extends BaseColumns {
        String TABLE_NAME = "restaurant_rating";
        String COLUMN_RESTAURANT_ID = "restaurant_id";
        String COLUMN_RATER_ID = "rater_id";
        String COLUMN_SERVICE_QUALITY = "service_quality";
        String COLUMN_FOOD_QUALITY = "food_rating";
        String COLUMN_AVERAGE_PRICE = "avg_price";
        String COLUMN_STARS = "stars";
    }
}
