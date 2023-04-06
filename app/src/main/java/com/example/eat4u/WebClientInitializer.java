package com.example.eat4u;

import android.content.Context;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.LocalWebClient;

public class WebClientInitializer {
    private static WebClient localWebClient;

    public static void init(Context context) {
        localWebClient = new LocalWebClient(context);
    }

    public static WebClient getInstance() {
        return localWebClient;
    }
}
