package com.example.eat4u.backend;


import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

public interface WebClient {

    boolean submitReview(Restaurant restaurant);

    RestaurantList loadRestaurants();
}
