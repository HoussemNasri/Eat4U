package com.example.eat4u;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

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
    private DrawerLayout drawer;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.drawer = findViewById(R.id.drawer);
        this.toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
    }
}