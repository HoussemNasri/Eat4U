package com.example.eat4u.model;

import java.util.List;

public record Restaurant(
        Long id,
        String name,
        String address,
        List<RestaurantRating> ratings
) {
}
