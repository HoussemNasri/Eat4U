package com.example.eat4u.backend;

@FunctionalInterface
public interface DataCallback<T> {
    void onDataFetched(T data);
}
