package com.example.eat4u.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.tasks.LoadRestaurantsTask;
import com.example.eat4u.model.RestaurantList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<RestaurantList> _restaurantsLiveData = new MutableLiveData<>();
    private final WebClient WebClient;

    public HomeViewModel(WebClient WebClient) {
        this.WebClient = WebClient;
    }

    public LiveData<RestaurantList> getRestaurantsList() {
        new LoadRestaurantsTask(WebClient, restaurants -> _restaurantsLiveData.setValue(restaurants)).execute();
        return _restaurantsLiveData;
    }

}
