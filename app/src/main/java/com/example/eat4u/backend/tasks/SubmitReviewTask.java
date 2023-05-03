package com.example.eat4u.backend.tasks;

import android.os.AsyncTask;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Restaurant;

public class SubmitReviewTask extends AsyncTask<Void, Void, Boolean> {
    private final WebClient webClient;
    private final Restaurant restaurant;
    private final DataCallback<Boolean> submissionStatusCallback;

    public SubmitReviewTask(WebClient webClient, Restaurant restaurant, DataCallback<Boolean> submissionStatusCallback) {
        this.webClient = webClient;
        this.restaurant = restaurant;
        this.submissionStatusCallback = submissionStatusCallback;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
       return webClient.submitReview(restaurant);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        submissionStatusCallback.onDataFetched(aBoolean);
    }
}
