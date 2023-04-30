package com.example.eat4u.ui.restaurant_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.eat4u.R;
import com.example.eat4u.backend.LocalWebClient;
import com.example.eat4u.backend.WebClient;

public class RestaurantListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RestaurantListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_list);

        RestaurantListViewModelFactory viewModelFactory = new RestaurantListViewModelFactory(new LocalWebClient(this));
        viewModel = viewModelFactory.create(RestaurantListViewModel.class);
        RestaurantListAdapter adapter = new RestaurantListAdapter(this, (position) -> System.out.println());
        viewModel.getRestaurantListLiveData().observe(this, adapter::setRestaurantList);
        this.recyclerView = findViewById(R.id.restaurants_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}