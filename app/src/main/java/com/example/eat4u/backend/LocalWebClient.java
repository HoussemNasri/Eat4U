package com.example.eat4u.backend;

import android.content.Context;

import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.backend.db.entities.RestaurantEntity;
import com.example.eat4u.backend.dto.SubmitReviewRequest;
import com.example.eat4u.model.Restaurant;

import org.jetbrains.annotations.NotNull;

/**
 * A mock implementation of web client backed by an SQLite database
 */
public class LocalWebClient implements WebClient {
    private final DatabaseHelper databaseHelper;

    public LocalWebClient(@NotNull Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public boolean submitReview(Restaurant restaurant) {
        databaseHelper.storeRestaurant(
                new RestaurantEntity(
                        restaurant.getFoodQuality(),
                        restaurant.getServiceQuality(),
                        restaurant.getStars(),
                        restaurant.getAveragePrice(),
                        restaurant.getId(),
                        restaurant.getName(),
                        restaurant.getAddress())
        );

        return true;
    }

}
