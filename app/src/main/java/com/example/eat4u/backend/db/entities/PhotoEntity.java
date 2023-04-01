package com.example.eat4u.backend.db.entities;

public class PhotoEntity {

    private final Long id;
    private final String url;
    private final Long restaurantId;

    public PhotoEntity(Long id, String url, Long restaurantId) {
        this.id = id;
        this.url = url;
        this.restaurantId = restaurantId;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }
}
