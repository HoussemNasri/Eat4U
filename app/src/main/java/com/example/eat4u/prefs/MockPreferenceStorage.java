package com.example.eat4u.prefs;

import com.example.eat4u.backend.LocalWebClient;
import com.example.eat4u.model.User;

import java.util.Optional;

/**
 * I'm too lazy to implement cache the user object properly...
 */
public class MockPreferenceStorage implements PreferenceStorage {
    @Override
    public Optional<User> getCurrentUser() {
        return Optional.of(LocalWebClient.TEST_USER);
    }
}
