package com.example.eat4u;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantRating;
import com.example.eat4u.model.Stars;
import com.example.eat4u.model.User;
import com.example.eat4u.data.db.DatabaseHelper;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    private Logger LOGGER = Logger.getLogger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try (DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext())) {
            User u1 = new User(455L, "Houssem", "Nasri", "houssem@gmail.com", "22222222");
            User u2 = new User(421L, "Dhia", "Kassab", "kassab@gmail.com", "11111111");
            RestaurantRating rating1 = new RestaurantRating(Quality.AVERAGE, Quality.GOOD, Stars.TWO, 2.25d, u1);

            Restaurant r1 = new Restaurant(100L, "BURGER KING", "2489 San Diego Ave, San Diego, CA 92110");
            Restaurant r2 = new Restaurant(122L, "Buenos Aires Pizzeria", "1307 22nd St, Denver, CO 80205");
            Restaurant r3 = new Restaurant(302L, "Zola", "800 F. Street, NW, Washington, DC 20004");
            new AsyncTask<String, String, String>() {

                @Override
                protected String doInBackground(String... strings) {
                    databaseHelper.saveRestaurant(r1);
                    databaseHelper.saveRestaurant(r2);
                    databaseHelper.saveRestaurant(r3);

                    LOGGER.info("Added stuff to db");
                    return "";
                }
            }.execute("");

        }

    }
}