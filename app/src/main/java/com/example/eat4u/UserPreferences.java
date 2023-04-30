package com.example.eat4u;

public class UserPreferences {
    private static User _user;

    public static void saveUser(User user) {
        _user = user;
    }

    public static User getCurrentUser() {
        return _user;
    }
}
