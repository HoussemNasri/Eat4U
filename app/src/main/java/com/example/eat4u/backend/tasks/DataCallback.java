package com.example.eat4u.backend.tasks;

@FunctionalInterface
public interface DataCallback<T> {
    void onDataFetched(T data);
}
