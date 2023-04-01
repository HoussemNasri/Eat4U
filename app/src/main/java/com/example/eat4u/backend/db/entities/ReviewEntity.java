package com.example.eat4u.backend.db.entities;

import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Stars;

public class ReviewEntity {

    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;
    private final Long raterId;
    private final Long restaurantId;


    public ReviewEntity(Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice, Long raterId, Long restaurantId) {
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.stars = stars;
        this.averagePrice = averagePrice;
        this.raterId = raterId;
        this.restaurantId = restaurantId;
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

    public Long getRaterId() {
        return raterId;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }
}
