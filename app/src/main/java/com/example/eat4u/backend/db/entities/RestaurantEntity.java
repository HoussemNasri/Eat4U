package com.example.eat4u.backend.db.entities;

public class RestaurantEntity {
    private final Long id;
    private final String name;
    private final String description;
    private final String address;


    public RestaurantEntity(Long id, String name, String description, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }
}
