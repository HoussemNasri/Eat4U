package com.example.eat4u.prefs;

import com.example.eat4u.model.User;

import java.util.Optional;

public interface PreferenceStorage {
    Optional<User> getCurrentUser();
}
