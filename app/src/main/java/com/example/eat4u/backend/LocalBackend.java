package com.example.eat4u.backend;

import android.content.Context;
import android.os.AsyncTask;

import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * A mock implementation of the backend server that stores data in a local sqlite database
 */
public class LocalBackend implements Backend {
    private final Context context;
    private final DatabaseHelper databaseHelper;

    public LocalBackend(@NotNull Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public RestaurantList getRestaurants() {
        return null;
    }

    @Override
    public boolean rate(Long restaurantId, SubmitRatingRequest rateDTO) {
        return false;
    }

    @Override
    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return Optional.empty();
    }
}
