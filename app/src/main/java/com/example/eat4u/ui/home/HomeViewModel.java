package com.example.eat4u.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.Backend;
import com.example.eat4u.backend.LoadRestaurantsTask;
import com.example.eat4u.model.RestaurantList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<RestaurantList> _restaurantsLiveData = new MutableLiveData<>();
    private final Backend backend;

    public HomeViewModel(Backend backend) {
        this.backend = backend;
    }

    public LiveData<RestaurantList> getRestaurantsList() {
        new LoadRestaurantsTask(backend, restaurants -> _restaurantsLiveData.setValue(restaurants)).execute();

        return _restaurantsLiveData;
    }

}
