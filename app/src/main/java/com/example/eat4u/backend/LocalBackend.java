package com.example.eat4u.backend;

import android.content.Context;

import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.backend.db.entities.PhotoEntity;
import com.example.eat4u.backend.db.entities.RestaurantEntity;
import com.example.eat4u.model.Location;
import com.example.eat4u.model.Photo;
import com.example.eat4u.model.PhotoAlbum;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A mock implementation of the backend server that stores data in a local sqlite database
 */
public class LocalBackend implements Backend {
    private final DatabaseHelper databaseHelper;

    public LocalBackend(@NotNull Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public RestaurantList getRestaurants() {
        List<Restaurant> restaurants = databaseHelper.getAllRestaurants().stream()
                .map(this::mapRestaurantEntityToRestaurant).collect(Collectors.toList());
        return new RestaurantList(restaurants);
    }

    private Restaurant mapRestaurantEntityToRestaurant(RestaurantEntity restaurant) {
        return new Restaurant(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                restaurant.getDescription(),
                Location.of("", ""),
                databaseHelper.countRestaurantReviews(restaurant.getId()),
                databaseHelper.computeRestaurantReviewsAverage(restaurant.getId()),
                getRestaurantPhotos(restaurant.getId()));
    }

    @Override
    public boolean rate(Long restaurantId, SubmitRatingRequest rateDTO) {
        return false;
    }

    @Override
    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return Optional.empty();
    }

    @Override
    public PhotoAlbum getRestaurantPhotos(Long restaurantId) {
        List<Photo> photos = databaseHelper.getRestaurantPhotos(restaurantId).stream()
                .map(this::mapPhotoEntityToPhoto).collect(Collectors.toList());
        return new PhotoAlbum(photos);
    }

    private Photo mapPhotoEntityToPhoto(PhotoEntity photo) {
        return new Photo(photo.getId(), photo.getUrl());
    }


}
