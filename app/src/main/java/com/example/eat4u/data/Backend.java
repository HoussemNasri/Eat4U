package com.example.eat4u.data;

import com.example.eat4u.model.RestaurantList;

import java.util.Optional;

public interface Backend {
    /**
     * Fetches the list of restaurants information from the server.
     */
    RestaurantList getRestaurants();

    /**
     * Rate the specified {@code restaurantId} with the supplied rating parameters in {@code rateDTO}.
     *
     * @param rateDTO      encapsulates the parameters of a user rating
     * @param restaurantId the id of the restaurant to rate
     * @return {@code True} if the operator succeed, and {@code False} otherwise
     */
    boolean rate(Long restaurantId, RateRequest rateDTO);

    /**
     * Authenticate user and provide an authentication token for further secure client/server communication.
     *
     * @param authenticationRequest encapsulates user information used for authentication
     * @return If authentication succeed, returns the {@code authenticationResponse} object wrapped
     * in an {@link Optional}, otherwise it returns {@link  Optional#empty()}
     */
    Optional<AuthenticationResponse> authenticate(AuthenticationRequest authenticationRequest);


}
