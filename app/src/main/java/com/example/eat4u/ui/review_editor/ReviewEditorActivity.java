package com.example.eat4u.ui.review_editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eat4u.WebClientInitializer;
import com.example.eat4u.R;
import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.Stars;
import com.example.eat4u.prefs.MockPreferenceStorage;
import com.example.eat4u.prefs.PreferenceStorage;
import com.example.eat4u.utils.Globals;
import com.google.android.material.textfield.TextInputEditText;

public class ReviewEditorActivity extends AppCompatActivity {
    private Restaurant toReviewRestaurant;
    private TextView toReviewRestaurantNameTextView;
    private TextView lastEditedReviewDateTextView;
    private RatingBar foodQualityRatingBar;
    private RatingBar serviceQualityRatingBar;
    private RatingBar starsRatingBar;
    private TextInputEditText averagePriceEditText;
    private ReviewEditorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_editor);

        toReviewRestaurantNameTextView = findViewById(R.id.restaurant_to_rate_name_text);
        lastEditedReviewDateTextView = findViewById(R.id.rating_last_edited_text);
        foodQualityRatingBar = findViewById(R.id.food_qualty_rating_bar);
        serviceQualityRatingBar = findViewById(R.id.service_qualty_rating_bar);
        starsRatingBar = findViewById(R.id.stars_rating_bar);
        averagePriceEditText = findViewById(R.id.average_price_edit_text);

        WebClient webClient = WebClientInitializer.getInstance();
        PreferenceStorage preferenceStorage = new MockPreferenceStorage();
        ReviewEditorViewModelFactory viewModelFactory = new ReviewEditorViewModelFactory(webClient, preferenceStorage);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ReviewEditorViewModel.class);

        if (getIntent() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                toReviewRestaurant = getIntent().getExtras().getParcelable(Globals.RESTAURANT_EXTRA, Restaurant.class);
                viewModel.setRestaurant(toReviewRestaurant);
                viewModel.fetchLastSubmittedReview(toReviewRestaurant.getId());
                toReviewRestaurantNameTextView.setText(toReviewRestaurant.getName());
            }
        }

        viewModel.getFoodQualityLiveData().observe(this, foodQuality -> {
            switch (foodQuality) {
                case POOR:
                    foodQualityRatingBar.setRating(1f);
                    break;
                case AVERAGE:
                    foodQualityRatingBar.setRating(2f);
                    break;
                case GOOD:
                    foodQualityRatingBar.setRating(3f);
                    break;
                case EXCELLENT:
                    foodQualityRatingBar.setRating(4f);
                    break;
            }
        });

        viewModel.getServiceQualityLiveData().observe(this, serviceQuality -> {
            switch (serviceQuality) {
                case POOR:
                    serviceQualityRatingBar.setRating(1f);
                    break;
                case AVERAGE:
                    serviceQualityRatingBar.setRating(2f);
                    break;
                case GOOD:
                    serviceQualityRatingBar.setRating(3f);
                    break;
                case EXCELLENT:
                    serviceQualityRatingBar.setRating(4f);
                    break;
            }
        });

        viewModel.getStarsLiveData().observe(this, stars -> {
            starsRatingBar.setRating(stars.getInt());
        });

        foodQualityRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            viewModel.setFoodQuality(Quality.parse((long) rating));
        });

        serviceQualityRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            viewModel.setServiceQuality(Quality.parse((long) rating));
        });

        starsRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            viewModel.setStars(Stars.parse((int) rating));
        });
    }

    public void submitReview(View view) {
        viewModel.submitReview().observe(this, isSucceed -> {
            if (isSucceed) {
                Toast.makeText(this, "Review is submitted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Review submission failed. Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}