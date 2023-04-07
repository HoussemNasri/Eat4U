package com.example.eat4u.backend.dto;

import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.Stars;

public class SubmitReviewRequest {

    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;
    private final Restaurant restaurant;

    public SubmitReviewRequest(Restaurant restaurant, Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
        this.restaurant = restaurant;
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

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public static SubmitReviewRequest from(Review review, Restaurant restaurant) {
        return new SubmitReviewRequest(
                restaurant,
                review.getFoodQuality(),
                review.getServiceQuality(),
                review.getStars(),
                review.getAveragePrice()

        );
    }
}
