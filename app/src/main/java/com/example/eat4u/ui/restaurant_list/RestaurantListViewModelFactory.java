package com.example.eat4u.ui.restaurant_list;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Restaurant;

public class RestaurantListViewModelFactory implements ViewModelProvider.Factory {
    private final WebClient webClient;

    public RestaurantListViewModelFactory(WebClient webClient) {
        this.webClient = webClient;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new RestaurantListViewModel(webClient);
    }
}
