package com.example.eat4u.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.LoadRestaurantsTask;
import com.example.eat4u.model.RestaurantList;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<RestaurantList> _restaurantsLiveData = new MutableLiveData<>();

    public LiveData<RestaurantList> getRestaurantsList() {
        new LoadRestaurantsTask(null, restaurants -> _restaurantsLiveData.setValue(restaurants)).execute();

        return _restaurantsLiveData;
    }

}
