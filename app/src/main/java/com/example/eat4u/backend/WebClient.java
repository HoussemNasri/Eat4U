package com.example.eat4u.backend;

import com.example.eat4u.backend.dto.SubmitReviewRequest;

public interface WebClient {

    /**
     * Rate the specified {@code restaurantId} with the supplied rating parameters in {@code submitReviewRequest}.
     *
     * @param submitReviewRequest encapsulates the parameters of a user review
     * @return {@code True} if the operator succeed, and {@code False} otherwise
     */
    boolean submitReview(SubmitReviewRequest submitReviewRequest);

}
