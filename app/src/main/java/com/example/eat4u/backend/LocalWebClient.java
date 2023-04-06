package com.example.eat4u.backend;

import android.content.Context;

import com.example.eat4u.R;
import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.backend.db.entities.PhotoEntity;
import com.example.eat4u.backend.db.entities.RestaurantEntity;
import com.example.eat4u.backend.db.entities.ReviewEntity;
import com.example.eat4u.backend.db.entities.UserEntity;
import com.example.eat4u.backend.dto.AuthenticationRequest;
import com.example.eat4u.backend.dto.AuthenticationResponse;
import com.example.eat4u.backend.dto.SubmitReviewRequest;
import com.example.eat4u.backend.dto.SubmitReviewResponse;
import com.example.eat4u.model.Location;
import com.example.eat4u.model.Photo;
import com.example.eat4u.model.PhotoAlbum;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.User;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A mock implementation of web client backed by an SQLite database
 */
public class LocalWebClient implements WebClient {
    public static final User TEST_USER = new User(1L, "Bernfried", "Schock", "bernfried.schock@example.com",
            "0859-6994128", "https://randomuser.me/api/portraits/men/49.jpg");
    private final DatabaseHelper databaseHelper;

    public LocalWebClient(@NotNull Context context) {
        this.databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public RestaurantList getRestaurants() {
        List<Restaurant> restaurants = databaseHelper.getAllRestaurants().stream().map(this::mapRestaurantEntityToRestaurant).collect(Collectors.toList());
        return new RestaurantList(restaurants);
    }

    private Restaurant mapRestaurantEntityToRestaurant(RestaurantEntity restaurant) {
        return new Restaurant(restaurant.getId(), restaurant.getName(), restaurant.getAddress(), restaurant.getDescription(), Location.of("", ""), databaseHelper.countRestaurantReviews(restaurant.getId()), databaseHelper.computeRestaurantReviewsAverage(restaurant.getId()), getRestaurantPhotos(restaurant.getId()));
    }

    @Override
    public Optional<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest) {
        return Optional.empty();
    }

    @Override
    public PhotoAlbum getRestaurantPhotos(Long restaurantId) {
        List<Photo> photos = databaseHelper.getRestaurantPhotos(restaurantId).stream().map(this::mapPhotoEntityToPhoto).collect(Collectors.toList());
        return new PhotoAlbum(photos);
    }

    @Override
    public boolean submitReview(SubmitReviewRequest submitReviewRequest) {
        Optional<User> authenticatedUserOpt = getAuthenticatedUser();
        if (!authenticatedUserOpt.isPresent()) {
            return false;
        }

        User authenticatedUser = authenticatedUserOpt.get();

        databaseHelper.upsertReview(new ReviewEntity(
                submitReviewRequest.getFoodQuality(),
                submitReviewRequest.getServiceQuality(),
                submitReviewRequest.getStars(),
                submitReviewRequest.getAveragePrice(),
                authenticatedUser.getId(),
                submitReviewRequest.getRestaurantId()
        ));

        return true;
    }

    @Override
    public Optional<User> getAuthenticatedUser() {
        return Optional.of(TEST_USER);
    }

    @Override
    public Review getReview(Long userId, Long restaurantId) {
        return databaseHelper.getReview(restaurantId, userId).map(this::mapReviewEntityToReview).orElse(null);
    }

    private Review mapReviewEntityToReview(ReviewEntity review) {
        return new Review(
                review.getFoodQuality(),
                review.getServiceQuality(),
                review.getStars(),
                review.getAveragePrice(),
                // TODO: Fetch associated rater user object
                null
        );
    }

    private Photo mapPhotoEntityToPhoto(PhotoEntity photo) {
        return new Photo(photo.getId(), photo.getUrl());
    }


}
