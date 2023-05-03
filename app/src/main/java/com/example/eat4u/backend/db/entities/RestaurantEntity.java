package com.example.eat4u.backend.db.entities;

import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Stars;

public class RestaurantEntity {

    private final Long id;
    private final String restaurantName;
    private final String restaurantAddress;
    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;


    public RestaurantEntity(Long id, String restaurantName, String restaurantAddress, Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.stars = stars;
        this.averagePrice = averagePrice;
        this.id = id;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
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

    public Long geRestaurantId() {
        return id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }
}
