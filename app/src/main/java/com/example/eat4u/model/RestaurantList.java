package com.example.eat4u.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RestaurantList extends ArrayList<Restaurant> {

    public RestaurantList(List<Restaurant> restaurants) {
        super(restaurants);
    }

    public RestaurantList(Restaurant... restaurants) {
        super(Arrays.asList(restaurants));
    }

    public static RestaurantList empty() {
        return new RestaurantList();
    }
}
