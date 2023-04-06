package com.example.eat4u.backend.dto;

import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Stars;

public class SubmitReviewRequest {

    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;

    public SubmitReviewRequest(Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
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
}
