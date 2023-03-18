package com.example.eat4u.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private final Long id;
    private final String name;
    private final String address;

    private final List<RestaurantRating> ratings = new ArrayList<>();

    public Restaurant(Long id, String name, String address, List<RestaurantRating> ratings) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.ratings.addAll(ratings);
    }

    public Restaurant(Long id, String name, String address) {
        this(id, name, address, Collections.emptyList());
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

    public List<RestaurantRating> getRatings() {
        return Collections.unmodifiableList(ratings);
    }
}

