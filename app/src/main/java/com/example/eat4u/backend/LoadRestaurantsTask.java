package com.example.eat4u.backend;

import android.os.AsyncTask;

import com.example.eat4u.model.RestaurantList;

public class LoadRestaurantsTask extends AsyncTask<Void, Void, RestaurantList> {
    private final Backend backend;
    private final DataCallback<RestaurantList> dataCallback;

    public LoadRestaurantsTask(Backend backend, DataCallback<RestaurantList> dataCallback) {
        this.backend = backend;
        this.dataCallback = dataCallback;
    }

    @Override
    protected RestaurantList doInBackground(Void... voids) {
        return backend.getRestaurants();
    }

    @Override
    protected void onPostExecute(RestaurantList restaurantList) {
        super.onPostExecute(restaurantList);
        dataCallback.onDataFetched(restaurantList);
    }
}
