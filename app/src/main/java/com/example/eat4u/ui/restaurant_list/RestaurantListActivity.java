package com.example.eat4u.ui.restaurant_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.eat4u.R;
import com.example.eat4u.backend.LocalWebClient;
import com.example.eat4u.backend.WebClient;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;
import com.example.eat4u.ui.review_editor.ReviewEditorActivity;
import com.example.eat4u.utils.Globals;

public class RestaurantListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantListViewModel viewModel;
    private RestaurantListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);

        this.recyclerView = findViewById(R.id.restaurants_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RestaurantListViewModelFactory viewModelFactory = new RestaurantListViewModelFactory(new LocalWebClient(this));
        viewModel = viewModelFactory.create(RestaurantListViewModel.class);
        viewModel.getRestaurantListLiveData().observe(this, restaurantList -> {
            if (adapter == null) {
                adapter = new RestaurantListAdapter(this, (position) -> navigateToReviewEditor(restaurantList.get(position)));
                recyclerView.setAdapter(adapter);
            }
            adapter.setRestaurantList(restaurantList);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.refetchRestaurants();
    }

    private void navigateToReviewEditor(Restaurant restaurant) {
        Intent intent = new Intent(this, ReviewEditorActivity.class);
        intent.putExtra(Globals.RESTAURANT_EXTRA, restaurant);
        startActivity(intent);
    }
}