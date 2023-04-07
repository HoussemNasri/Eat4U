package com.example.eat4u.ui.review_editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eat4u.R;
import com.example.eat4u.backend.LocalWebClient;
import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Stars;
import com.example.eat4u.utils.Globals;
import com.example.eat4u.utils.StringUtils;
import com.google.android.material.textfield.TextInputEditText;

public class ReviewEditorActivity extends AppCompatActivity {
    private TextView userNameTextView;
    private RatingBar foodQualityRatingBar;
    private RatingBar serviceQualityRatingBar;
    private RatingBar starsRatingBar;
    private TextInputEditText averagePriceEditText;
    private TextInputEditText restaurantNameEditText;
    private TextInputEditText restaurantAddressEditText;
    private ReviewEditorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_editor);

        userNameTextView = findViewById(R.id.user_name_textview);
        foodQualityRatingBar = findViewById(R.id.food_qualty_rating_bar);
        serviceQualityRatingBar = findViewById(R.id.service_qualty_rating_bar);
        starsRatingBar = findViewById(R.id.stars_rating_bar);
        averagePriceEditText = findViewById(R.id.average_price_edit_text);
        restaurantNameEditText = findViewById(R.id.restaurant_name_edittext);
        restaurantAddressEditText = findViewById(R.id.restaurant_address_edit_text);

        averagePriceEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (averagePriceEditText.getText() != null &&
                        StringUtils.isBlankOrNull(averagePriceEditText.getText().toString())) {
                    viewModel.setAveragePrice(0d);
                }
                viewModel.setAveragePrice(Double.valueOf(averagePriceEditText.getText().toString()));
            }

        });

        WebClient webClient = new LocalWebClient(this);
        ReviewEditorViewModelFactory viewModelFactory = new ReviewEditorViewModelFactory(webClient);
        viewModel = new ViewModelProvider(this, viewModelFactory).get(ReviewEditorViewModel.class);

        if (getIntent() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                String userFirstname = getIntent().getStringExtra(Globals.USER_FIRSTNAME_EXTRA);
                String userLastname = getIntent().getStringExtra(Globals.USER_LASTNAME_EXTRA);

                userNameTextView.setText(userFirstname + " " + userLastname);
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

        viewModel.getAveragePriceLiveData().observe(this, averagePrice -> {
            averagePriceEditText.setText(averagePrice == null ? 0 + "" : averagePrice.toString());
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
        viewModel.submitReview(restaurantNameEditText.getText().toString(), restaurantAddressEditText.getText().toString()).observe(this, isSucceed -> {
            if (isSucceed) {
                Toast.makeText(this, "Review is submitted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Review submission failed. Please try again!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}