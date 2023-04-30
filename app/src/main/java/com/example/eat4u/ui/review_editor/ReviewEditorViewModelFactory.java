package com.example.eat4u.ui.review_editor;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Restaurant;

public class ReviewEditorViewModelFactory implements ViewModelProvider.Factory {
    private final WebClient webClient;
    private final Restaurant restaurant;

    public ReviewEditorViewModelFactory(WebClient webClient, Restaurant restaurant) {
        this.webClient = webClient;
        this.restaurant = restaurant;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ReviewEditorViewModel(webClient, restaurant);
    }
}
