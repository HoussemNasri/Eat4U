package com.example.eat4u.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.eat4u.R;
import com.example.eat4u.backend.Backend;
import com.example.eat4u.backend.LocalBackend;
import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.model.RestaurantList;

import java.util.logging.Logger;

public class HomeActivity extends AppCompatActivity {
    // Use this flag to forcefully reset the database and repopulating it with mock data
    private static boolean shouldResetDatabase = false;
    private static Logger LOGGER = Logger.getLogger(HomeActivity.class.getSimpleName());

    private RecyclerView restaurantsRecyclerView;
    private RestaurantListAdapter restaurantListAdapter;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backend backend = new LocalBackend(this);
        HomeViewModelFactory homeViewModelFactory = new HomeViewModelFactory(backend);

        homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);

        restaurantListAdapter = new RestaurantListAdapter(this, new RestaurantList());

        restaurantsRecyclerView = findViewById(R.id.restaurants_recycler_view);
        restaurantsRecyclerView.setAdapter(restaurantListAdapter);
        restaurantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        homeViewModel.getRestaurantsList().observe(this, restaurants -> restaurantListAdapter.setRestaurantList(restaurants));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (shouldResetDatabase) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    new DatabaseHelper(HomeActivity.this.getApplicationContext()).reset();
                    return null;
                }
            }.execute();
        }
    }
}