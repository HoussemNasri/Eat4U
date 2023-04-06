package com.example.eat4u.model;

public enum Quality {
    EXCELLENT,
    GOOD,
    AVERAGE,
    POOR;

    public static Quality parse(String raw) {
        return Quality.valueOf(raw);
    }
}
