package com.example.eat4u.utils;

public class StringUtils {
    // Class shouldn't be instantiated
    private StringUtils() {
    }

    public static boolean isEmptyOrNull(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isBlankOrNull(String s) {
        return s == null || s.isBlank();
    }

    public static boolean isNotBlank(String s) {
        return !isBlankOrNull(s);
    }

    public static boolean isNotEmpty(String s) {
        return !isEmptyOrNull(s);
    }
}
