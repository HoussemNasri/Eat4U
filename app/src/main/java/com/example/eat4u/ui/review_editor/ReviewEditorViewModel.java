package com.example.eat4u.ui.review_editor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.tasks.SubmitReviewTask;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.Stars;

public class ReviewEditorViewModel extends ViewModel {
    private MutableLiveData<Quality> foodQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Quality> serviceQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Stars> starsLiveData = new MutableLiveData<>();
    private MutableLiveData<Double> averagePriceLiveData = new MutableLiveData<>();

    private MutableLiveData<String> restaurantNameLiveData = new MutableLiveData<>();
    private MutableLiveData<String> restaurantAddressLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> reviewSubmissionStatus = new MutableLiveData<>();
    private final WebClient webClient;
    private final Restaurant restaurant;

    public ReviewEditorViewModel(WebClient webClient, Restaurant restaurant) {
        this.webClient = webClient;

        if (restaurant == null) {
            this.restaurant = new Restaurant(-1L, "", "", Quality.POOR, Quality.POOR, Stars.ZERO, 0d);
        } else {
            this.restaurant = restaurant;
        }

        foodQualityLiveData.setValue(this.restaurant.getFoodQuality());
        serviceQualityLiveData.setValue(this.restaurant.getServiceQuality());
        starsLiveData.setValue(this.restaurant.getStars());
        averagePriceLiveData.setValue(this.restaurant.getAveragePrice());
    }

    public LiveData<Quality> getFoodQualityLiveData() {
        return foodQualityLiveData;
    }

    public void setFoodQuality(Quality foodQuality) {
        foodQualityLiveData.setValue(foodQuality);
    }

    public LiveData<Quality> getServiceQualityLiveData() {
        return serviceQualityLiveData;
    }

    public void setServiceQuality(Quality serviceQuality) {
        serviceQualityLiveData.setValue(serviceQuality);
    }

    public LiveData<Stars> getStarsLiveData() {
        return starsLiveData;
    }

    public void setStars(Stars stars) {
        starsLiveData.setValue(stars);
    }

    public LiveData<Double> getAveragePriceLiveData() {
        return averagePriceLiveData;
    }

    public void setAveragePrice(Double value) {
        if (value.equals(averagePriceLiveData.getValue())) {
            return;
        }
        averagePriceLiveData.setValue(value);
    }

    public LiveData<String> getRestaurantNameLiveData() {
        return restaurantAddressLiveData;
    }

    public void setRestaurantName(String name) {
        restaurantNameLiveData.setValue(name);
    }

    public LiveData<String> getRestaurantAddressLiveData() {
        return restaurantAddressLiveData;
    }

    public void setRestaurantAddress(String address) {
        restaurantAddressLiveData.setValue(address);
    }

    public MutableLiveData<Boolean> submitReview() {
        restaurant.setAveragePrice(averagePriceLiveData.getValue());
        restaurant.setFoodQuality(foodQualityLiveData.getValue());
        restaurant.setServiceQuality(serviceQualityLiveData.getValue());
        restaurant.setStars(starsLiveData.getValue());
        restaurant.setName(restaurantNameLiveData.getValue());
        restaurant.setAddress(restaurantAddressLiveData.getValue());

        new SubmitReviewTask(webClient, restaurant, reviewSubmissionStatus::setValue).execute();
        return reviewSubmissionStatus;
    }

}
