package com.example.eat4u.ui.review_editor;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.prefs.PreferenceStorage;

public class ReviewEditorViewModelFactory implements ViewModelProvider.Factory {
    private final WebClient webClient;
    private final PreferenceStorage preferenceStorage;

    public ReviewEditorViewModelFactory(WebClient webClient, PreferenceStorage preferenceStorage) {
        this.webClient = webClient;
        this.preferenceStorage = preferenceStorage;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ReviewEditorViewModel(webClient, preferenceStorage);
    }
}
