package com.example.eat4u.model;

public class Photo {
    private final Long id;
    private final String url;

    public Photo(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
