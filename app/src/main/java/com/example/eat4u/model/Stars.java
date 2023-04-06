package com.example.eat4u.model;

public enum Stars {
    ZERO,
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public static Stars parse(String raw) {
        return Stars.valueOf(raw);
    }
}
