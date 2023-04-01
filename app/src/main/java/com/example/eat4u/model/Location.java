package com.example.eat4u.model;

public class Location {
    private final String longitude;
    private final String latitude;

    public Location(String longitude, String latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public static Location of(String longitude, String latitude) {
        return new Location(longitude, latitude);
    }

    public float computeDistanceToTargetInMeters(Location target) {
        return 12f;
    }
}
