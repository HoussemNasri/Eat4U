package com.example.eat4u.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RestaurantList {
    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantList(List<Restaurant> restaurants) {
        this.restaurants.addAll(restaurants);
    }

    public RestaurantList(Restaurant... restaurants) {
        this(Arrays.asList(restaurants));
    }

    public List<Restaurant> getRestaurants() {
        return Collections.unmodifiableList(restaurants);
    }
}
