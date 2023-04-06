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

    public static Stars parse(Integer value) {
        if (!(value >= 0 && value <= 5)) {
            throw new IllegalArgumentException();
        }
        return Stars.values()[value];
    }

    public int getInt() {
        return ordinal();
    }
}
