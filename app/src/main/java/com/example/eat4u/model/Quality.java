package com.example.eat4u.model;

public enum Quality {
    EXCELLENT,
    GOOD,
    AVERAGE,
    POOR;

    public static Quality parse(String raw) {
        return Quality.valueOf(raw);
    }

    public static Quality parse(Long value) {
        if (value == 1) {
            return POOR;
        } else if (value == 2) {
            return AVERAGE;
        } else if (value == 3) {
            return GOOD;
        } else if (value == 4) {
            return EXCELLENT;
        } else {
            throw new IllegalArgumentException("Quality values range from 1 to 4");
        }
    }
}
