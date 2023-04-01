package com.example.eat4u;

import com.example.eat4u.backend.Backend;
import com.example.eat4u.backend.LocalBackend;

public class BackendInitializer {

    public static Backend getBackend() {
        return new LocalBackend(null);
    }
}
