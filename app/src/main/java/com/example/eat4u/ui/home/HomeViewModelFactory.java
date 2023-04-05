package com.example.eat4u.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.eat4u.backend.Backend;

public class HomeViewModelFactory implements ViewModelProvider.Factory {
    private final Backend backend;

    public HomeViewModelFactory(Backend backend) {
        this.backend = backend;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(backend);
    }
}
