package com.example.eat4u.ui.restaurant_details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.eat4u.R;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.ui.review_editor.ReviewEditorActivity;
import com.example.eat4u.utils.Globals;
import com.example.eat4u.utils.StringUtils;
import com.smarteist.autoimageslider.SliderView;

public class RestaurantDetailsActivity extends AppCompatActivity {

    private Restaurant restaurant;
    private SliderView restaurantPhotosSlider;
    private TextView restaurantNameTextView;
    private TextView restaurantDescriptionTextView;
    private RecyclerView restaurantReviewsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        restaurantPhotosSlider = findViewById(R.id.restaurant_images_slider);
        restaurantNameTextView = findViewById(R.id.restaurant_name_text);
        restaurantDescriptionTextView = findViewById(R.id.restaurant_description_text);
        restaurantReviewsRecyclerView = findViewById(R.id.reviews_glimpse_recycler_view);

        if (getIntent() != null && getIntent().getExtras() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                restaurant = getIntent().getParcelableExtra(Globals.RESTAURANT_EXTRA, Restaurant.class);
                restaurantNameTextView.setText(restaurant.getName());
                if (StringUtils.isNotBlank(restaurant.getDescription())) {
                    restaurantDescriptionTextView.setText(restaurant.getDescription());
                } else {
                    restaurantDescriptionTextView.setText("Description not provided.");
                }

                RestaurantPhotosAdapter restaurantPhotosAdapter = new RestaurantPhotosAdapter(restaurant.getPhotoAlbum(), this);
                restaurantPhotosSlider.setSliderAdapter(restaurantPhotosAdapter);
            }
        }

    }

    public void onRateClicked(View view) {
        Intent intent = new Intent(this, ReviewEditorActivity.class);
        intent.putExtra(Globals.RESTAURANT_EXTRA, restaurant);
        startActivity(intent);
    }
}