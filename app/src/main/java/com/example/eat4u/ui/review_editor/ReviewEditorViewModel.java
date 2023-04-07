package com.example.eat4u.ui.review_editor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.tasks.SubmitReviewTask;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.Stars;

public class ReviewEditorViewModel extends ViewModel {
    private MutableLiveData<Review> reviewLiveData = new MutableLiveData<>(new Review(Quality.POOR, Quality.POOR, Stars.ZERO, 0.0));
    private MutableLiveData<Quality> foodQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Quality> serviceQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Stars> starsLiveData = new MutableLiveData<>();
    private MutableLiveData<Double> averagePriceLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> reviewSubmissionStatusLiveData = new MutableLiveData<>();
    private final WebClient webClient;

    public ReviewEditorViewModel(WebClient webClient) {
        this.webClient = webClient;

        reviewLiveData.observeForever(review -> {
            foodQualityLiveData.setValue(review.getFoodQuality());
            serviceQualityLiveData.setValue(review.getServiceQuality());
            starsLiveData.setValue(review.getStars());
            averagePriceLiveData.setValue(review.getAveragePrice());
        });
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

    public MutableLiveData<Boolean> submitReview(String restaurantName, String restaurantAddress) {
        new SubmitReviewTask(webClient, new Review(
                foodQualityLiveData.getValue(),
                serviceQualityLiveData.getValue(),
                starsLiveData.getValue(),
                averagePriceLiveData.getValue()
        ),
                new Restaurant(restaurantName, restaurantAddress), reviewSubmissionStatusLiveData::setValue).execute();
        return reviewSubmissionStatusLiveData;
    }

}
