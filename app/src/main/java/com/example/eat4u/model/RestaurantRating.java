package com.example.eat4u.model;

public class RestaurantRating {
    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;
    private final User rater;


    public RestaurantRating(Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice, User rater) {
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.stars = stars;
        this.averagePrice = averagePrice;
        this.rater = rater;
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

    public User getRater() {
        return rater;
    }
}
