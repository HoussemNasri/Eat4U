package com.example.eat4u.ui.restaurant_list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.tasks.LoadRestaurantsTask;
import com.example.eat4u.model.RestaurantList;

public class RestaurantListViewModel extends ViewModel {
    private final WebClient webClient;
    private MutableLiveData<RestaurantList> restaurantListLiveData = new MutableLiveData<>();

    public RestaurantListViewModel(WebClient webClient) {
        this.webClient = webClient;
        refetchRestaurants();
    }

    public void refetchRestaurants() {
        new LoadRestaurantsTask(webClient, restaurantListLiveData::setValue).execute();
    }

    public LiveData<RestaurantList> getRestaurantListLiveData() {
        return restaurantListLiveData;
    }
}
