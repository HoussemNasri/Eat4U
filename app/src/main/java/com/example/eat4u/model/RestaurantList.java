package com.example.eat4u.model;

import java.util.Arrays;
import java.util.List;

public record RestaurantList(
        List<Restaurant> restaurants
) {

    public RestaurantList(Restaurant... restaurants) {
        this(Arrays.asList(restaurants));
    }
}
