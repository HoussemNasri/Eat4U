package com.example.eat4u.backend.tasks;

import android.os.AsyncTask;

import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Review;

public class GetReviewTask extends AsyncTask<Void, Void, Review> {
    private final WebClient WebClient;
    private final Long currentUserId;
    private final Long restaurantId;

    private final DataCallback<Review> dataCallback;

    public GetReviewTask(WebClient WebClient, Long currentUserId, Long restaurantId, DataCallback<Review> dataCallback) {
        this.WebClient = WebClient;
        this.currentUserId = currentUserId;
        this.restaurantId = restaurantId;
        this.dataCallback = dataCallback;
    }

    @Override
    protected Review doInBackground(Void... voids) {
        return WebClient.getReview(currentUserId, restaurantId);
    }

    @Override
    protected void onPostExecute(Review review) {
        dataCallback.onDataFetched(review);
    }
}
