package com.example.eat4u.model;

import java.util.Optional;

public class Restaurant {
    private final Long id;
    private final String name;
    private final String address;

    private final Location exactLocation;

    private final float reviewsCount;

    private final float reviewsAverage;


    public Restaurant(Long id, String name, String address, Location exactLocation, float reviewsCount, float reviewsAverage) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.exactLocation = exactLocation;
        this.reviewsCount = reviewsCount;
        this.reviewsAverage = reviewsAverage;
    }

    public Restaurant(Long id, String name, String address) {
        this(id, name, address, null, 0, 0.0f);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Optional<Location> getExactLocation() {
        return Optional.ofNullable(exactLocation);
    }

    public float getReviewsCount() {
        return reviewsCount;
    }

    public float getReviewsAverage() {
        return reviewsAverage;
    }
}

