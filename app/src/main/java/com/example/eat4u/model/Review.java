package com.example.eat4u.model;

public class Review {
    private final Quality foodQuality;
    private final Quality serviceQuality;
    private final Stars stars;
    private final Double averagePrice;


    public Review(Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
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
