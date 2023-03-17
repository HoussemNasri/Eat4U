package com.example.eat4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.eat4u.model.Restaurant;
import com.example.eat4u.network.db.DatabaseHelper;

import java.util.Collections;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private Logger LOGGER = Logger.getLogger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext())) {
            Restaurant r1 = new Restaurant(100L, "SANDWICH KING", "100 S Marion St, Oak Park, IL 60302", Collections.emptyList());
            Restaurant r2 = new Restaurant(205L, "SUGAR RUSH", "8360 Melrose Ave #107, Los Angeles, CA 90069", Collections.emptyList());
            Restaurant r3 = new Restaurant(303L, "CHEF HUNTER", "2829 Ala Kalanikaumaka St. #G-149, Koloa, Kauai, HI 96756", Collections.emptyList());
            var task = new AsyncTask<String, String, String>() {

                @Override
                protected String doInBackground(String... strings) {
                    databaseHelper.saveRestaurant(r1);
                    databaseHelper.saveRestaurant(r2);
                    databaseHelper.saveRestaurant(r3);

                    LOGGER.info("Added 3 new restaurants!");
                    return "";
                }
            };

            task.execute("");

        }

    }
}