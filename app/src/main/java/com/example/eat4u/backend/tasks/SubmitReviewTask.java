package com.example.eat4u.backend.tasks;

import android.os.AsyncTask;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.backend.dto.SubmitReviewRequest;
import com.example.eat4u.model.Review;

public class SubmitReviewTask extends AsyncTask<Void, Void, Boolean> {
    private final WebClient webClient;
    private final Review review;
    private final Long restaurantId;

    private final DataCallback<Boolean> submissionStatusCallback;

    public SubmitReviewTask(WebClient webClient, Review review, Long restaurantId, DataCallback<Boolean> submissionStatusCallback) {
        this.webClient = webClient;
        this.review = review;
        this.restaurantId = restaurantId;
        this.submissionStatusCallback = submissionStatusCallback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
       return webClient.submitReview(SubmitReviewRequest.from(review, restaurantId));
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        submissionStatusCallback.onDataFetched(aBoolean);
    }
}
