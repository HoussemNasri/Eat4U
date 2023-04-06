package com.example.eat4u.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.eat4u.backend.WebClient;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private final WebClient WebClient;

    public HomeViewModelFactory(WebClient webClient) {
        this.WebClient = webClient;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(WebClient);
    }
}
