package com.example.eat4u.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import com.example.eat4u.R;
import com.example.eat4u.WebClientInitializer;
import com.example.eat4u.backend.db.DatabaseHelper;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;
import com.example.eat4u.ui.restaurant_details.RestaurantDetailsActivity;
import com.example.eat4u.utils.Globals;

import java.util.logging.Logger;

public class HomeActivity extends AppCompatActivity implements RestaurantListAdapter.RestaurantItemClickedListener {
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
        WebClientInitializer.init(getApplicationContext());

        HomeViewModelFactory homeViewModelFactory = new HomeViewModelFactory(WebClientInitializer.getInstance());

        homeViewModel = new ViewModelProvider(this, homeViewModelFactory).get(HomeViewModel.class);

        restaurantListAdapter = new RestaurantListAdapter(this, new RestaurantList(), this);

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

    @Override
    public void onRestaurantItemClicked(Restaurant restaurant) {
        Intent intent = new Intent(this, RestaurantDetailsActivity.class);
        System.out.println(restaurant);
        intent.putExtra(Globals.RESTAURANT_EXTRA, restaurant);
        startActivity(intent);
    }
}