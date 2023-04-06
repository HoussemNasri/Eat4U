package com.example.eat4u.backend;

import com.example.eat4u.backend.dto.AuthenticationRequest;
import com.example.eat4u.backend.dto.AuthenticationResponse;
import com.example.eat4u.backend.dto.SubmitReviewRequest;
import com.example.eat4u.backend.dto.SubmitReviewResponse;
import com.example.eat4u.model.PhotoAlbum;
import com.example.eat4u.model.RestaurantList;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.User;

import java.util.Optional;

public interface WebClient {
    /**
     * Fetches the list of restaurants information from the server.
     */
    RestaurantList getRestaurants();

    /**
     * Authenticate user and provide an authentication token for further secure client/server communication.
     *
     * @param authenticationRequest encapsulates user information used for authentication
     * @return If authentication succeed, returns the {@code authenticationResponse} object wrapped
     * in an {@link Optional}, otherwise it returns {@link  Optional#empty()}
     */
    Optional<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);

    PhotoAlbum getRestaurantPhotos(Long restaurantId);

    /**
     * Rate the specified {@code restaurantId} with the supplied rating parameters in {@code submitReviewRequest}.
     *
     * @param submitReviewRequest encapsulates the parameters of a user review
     * @return {@code True} if the operator succeed, and {@code False} otherwise
     */
    boolean submitReview(SubmitReviewRequest submitReviewRequest);

    Optional<User> getAuthenticatedUser();

    Review getReview(Long userId, Long restaurantId);

}
