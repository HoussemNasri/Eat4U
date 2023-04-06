package com.example.eat4u.backend.tasks;

import android.os.AsyncTask;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.RestaurantList;

public class LoadRestaurantsTask extends AsyncTask<Void, Void, RestaurantList> {
    private final WebClient WebClient;
    private final DataCallback<RestaurantList> dataCallback;

    public LoadRestaurantsTask(WebClient WebClient, DataCallback<RestaurantList> dataCallback) {
        this.WebClient = WebClient;
        this.dataCallback = dataCallback;
    }

    @Override
    protected RestaurantList doInBackground(Void... voids) {
        return WebClient.getRestaurants();
    }

    @Override
    protected void onPostExecute(RestaurantList restaurantList) {
        super.onPostExecute(restaurantList);
        dataCallback.onDataFetched(restaurantList);
    }
}
