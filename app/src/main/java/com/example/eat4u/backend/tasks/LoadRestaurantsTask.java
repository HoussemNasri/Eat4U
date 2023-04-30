package com.example.eat4u.backend.tasks;

import android.os.AsyncTask;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.RestaurantList;
import com.example.eat4u.ui.restaurant_list.RestaurantListViewModelFactory;

public class LoadRestaurantsTask extends AsyncTask<Void, Void, RestaurantList> {
    private final WebClient webClient;
    private final DataCallback<RestaurantList> callback;

    public LoadRestaurantsTask(WebClient webClient, DataCallback<RestaurantList> callback) {
        this.webClient = webClient;
        this.callback = callback;
    }


    @Override
    protected RestaurantList doInBackground(Void... voids) {
        return webClient.loadRestaurants();
    }

    @Override
    protected void onPostExecute(RestaurantList restaurantList) {
        callback.onDataFetched(restaurantList);
    }
}
