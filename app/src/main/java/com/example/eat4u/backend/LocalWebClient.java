package com.example.eat4u.backend;

import android.content.Context;

import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.backend.db.entities.RestaurantEntity;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;

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
        databaseHelper.upsertRestaurant(
                new RestaurantEntity(
                        restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getFoodQuality(),
                        restaurant.getServiceQuality(),
                        restaurant.getStars(),
                        restaurant.getAveragePrice()
                )
        );

        return true;
    }

    @Override
    public RestaurantList loadRestaurants() {
        return new RestaurantList(databaseHelper.getAllRestaurants().stream().map(entity ->
                new Restaurant(
                        entity.geRestaurantId(),
                        entity.getRestaurantName(),
                        entity.getRestaurantAddress(),
                        entity.getFoodQuality(),
                        entity.getServiceQuality(),
                        entity.getStars(),
                        entity.getAveragePrice())
        ).collect(Collectors.toList()));
    }
}
