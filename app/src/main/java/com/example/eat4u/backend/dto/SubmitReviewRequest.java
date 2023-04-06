package com.example.eat4u.backend.dto;

import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.Stars;

public class SubmitReviewRequest {

    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;
    private final Long restaurantId;

    public SubmitReviewRequest(Long restaurantId, Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
        this.restaurantId = restaurantId;
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.stars = stars;
        this.averagePrice = averagePrice;
    }

    public Quality getFoodQuality() {
        return foodQuality;
    }

    public Quality getServiceQuality() {
        return serviceQuality;
    }

    public Stars getStars() {
        return stars;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public static SubmitReviewRequest from(Review review, Long restaurantId) {
        return new SubmitReviewRequest(
                restaurantId,
                review.getFoodQuality(),
                review.getServiceQuality(),
                review.getStars(),
                review.getAveragePrice()

        );
    }
}
