package com.example.eat4u.ui.review_editor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.tasks.GetReviewTask;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.Stars;
import com.example.eat4u.model.User;
import com.example.eat4u.prefs.PreferenceStorage;

public class ReviewEditorViewModel extends ViewModel {
    private MutableLiveData<Review> reviewLiveData = new MutableLiveData<>();
    private MutableLiveData<Quality> foodQualityLiveData = new MutableLiveData<>();
    private MutableLiveData<Quality> serviceQualityLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> reviewSubmissionStatusLiveData = new MutableLiveData<>();
    private final WebClient webClient;
    private final PreferenceStorage preferenceStorage;

    public ReviewEditorViewModel(WebClient webClient, PreferenceStorage preferenceStorage) {
        this.webClient = webClient;
        this.preferenceStorage = preferenceStorage;

        reviewLiveData.observeForever(review -> {
            foodQualityLiveData.setValue(review.getFoodQuality());
            serviceQualityLiveData.setValue(review.getServiceQuality());
        });
    }

    public void fetchLastSubmittedReview(Long restaurantId) {
        User currentUser = preferenceStorage.getCurrentUser().get();
        new GetReviewTask(webClient, currentUser.getId(), restaurantId, review -> {
            System.out.println("Fetched Review: " + review);
            if (review == null) {
                reviewLiveData.setValue(new Review(Quality.POOR, Quality.POOR, Stars.ZERO, 0.0d, null));
            } else {
                reviewLiveData.setValue(review);
            }
        }).execute();
    }

    public LiveData<Quality> getFoodQualityLiveData() {
        return foodQualityLiveData;
    }

    public LiveData<Quality> getServiceQualityLiveData() {
        return serviceQualityLiveData;
    }

    public MutableLiveData<Boolean> submitReview() {

        return reviewSubmissionStatusLiveData;
    }

}
