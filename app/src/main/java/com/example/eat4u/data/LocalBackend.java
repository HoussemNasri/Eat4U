package com.example.eat4u.data;

import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A mock implementation of the backend server that stores data in a local sqlite database
 */
public class LocalBackend implements Backend {
    private static final List<Restaurant> restaurants = new ArrayList<>();

    static {
        // Load JSON file containing restaurants data and initialize the local backend state
        restaurants.add(null);
    }

    @Override
    public RestaurantList getRestaurants() {
        return new RestaurantList(Collections.unmodifiableList(restaurants));
    }

    @Override
    public boolean rate(Long restaurantId, RateRequest rateDTO) {
        return false;
    }

    @Override
    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return Optional.empty();
    }
}
