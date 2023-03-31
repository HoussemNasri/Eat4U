package com.example.eat4u.model;

import java.util.Collections;
import java.util.Optional;

public class Restaurant {
    private final Long id;
    private final String name;
    private final String address;

    private final Location exactLocation;

    private final float reviewsCount;

    private final float reviewsAverage;

    private final PhotoAlbum photoAlbum;


    public Restaurant(Long id, String name, String address, Location exactLocation, float reviewsCount, float reviewsAverage, PhotoAlbum photoAlbum) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.exactLocation = exactLocation;
        this.reviewsCount = reviewsCount;
        this.reviewsAverage = reviewsAverage;
        this.photoAlbum = photoAlbum;
    }

    public Restaurant(Long id, String name, String address) {
        this(id, name, address, null, 0, 0.0f, PhotoAlbum.empty());
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

    public PhotoAlbum getPhotoAlbum() {
        return photoAlbum;
    }
}

