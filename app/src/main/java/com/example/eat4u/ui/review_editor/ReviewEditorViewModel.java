package com.example.eat4u.ui.review_editor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.tasks.GetReviewTask;
import com.example.eat4u.backend.tasks.SubmitReviewTask;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.Stars;
import com.example.eat4u.model.User;
import com.example.eat4u.prefs.PreferenceStorage;

public class ReviewEditorViewModel extends ViewModel {
    private MutableLiveData<Review> reviewLiveData = new MutableLiveData<>();
    private MutableLiveData<Quality> foodQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Quality> serviceQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Stars> starsLiveData = new MutableLiveData<>();
    private MutableLiveData<Double> averagePriceLiveData = new MutableLiveData<>();
    private MutableLiveData<Restaurant> restaurantLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> reviewSubmissionStatusLiveData = new MutableLiveData<>();
    private final WebClient webClient;
    private final PreferenceStorage preferenceStorage;

    public ReviewEditorViewModel(WebClient webClient, PreferenceStorage preferenceStorage) {
        this.webClient = webClient;
        this.preferenceStorage = preferenceStorage;

        reviewLiveData.observeForever(review -> {
            foodQualityLiveData.setValue(review.getFoodQuality());
            serviceQualityLiveData.setValue(review.getServiceQuality());
            starsLiveData.setValue(review.getStars());
            averagePriceLiveData.setValue(review.getAveragePrice());
        });
    }

    public void fetchLastSubmittedReview(Long restaurantId) {
        User currentUser = preferenceStorage.getCurrentUser().get();
        new GetReviewTask(webClient, currentUser.getId(), restaurantId, review -> {
            if (review == null) {
                reviewLiveData.setValue(new Review(Quality.POOR, Quality.POOR, Stars.ZERO, 0.0, null));
            } else {
                reviewLiveData.setValue(review);
            }
        }).execute();
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

    public void setRestaurant(Restaurant restaurant) {
        restaurantLiveData.setValue(restaurant);
    }

    public MutableLiveData<Boolean> submitReview() {
        new SubmitReviewTask(webClient, new Review(
                foodQualityLiveData.getValue(),
                serviceQualityLiveData.getValue(),
                starsLiveData.getValue(),
                averagePriceLiveData.getValue(),
                null
        ),
                restaurantLiveData.getValue().getId(), reviewSubmissionStatusLiveData::setValue).execute();
        return reviewSubmissionStatusLiveData;
    }

}
