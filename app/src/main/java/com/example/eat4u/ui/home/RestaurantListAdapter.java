package com.example.eat4u.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.eat4u.R;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.Random;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    private final Context context;
    private RestaurantList restaurantList;

    public RestaurantListAdapter(Context context, @NotNull RestaurantList restaurantList) {
        this.context = context;
        this.restaurantList = Objects.requireNonNull(restaurantList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        return new ViewHolder(inflater.inflate(R.layout.restaurant_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Restaurant restaurant = restaurantList.getRestaurants().get(position);

        holder.restaurantNameTextView.setText(restaurant.getName());
        holder.restaurantStarsTextView.setText(String.format("%.1f (%d reviews)", new Random().nextDouble() * 5, new Random().nextInt(999)));
        holder.restaurantDistanceTextView.setText(String.format("%dm", new Random().nextInt(1000)));
        restaurant.getThumbnail().ifPresent(thumbnail -> {
            Glide.with(context).load(thumbnail.getUrl()).into(holder.restaurantImageView);
        });

    }

    @Override
    public int getItemCount() {
        return restaurantList.getRestaurants().size();
    }

    public void setRestaurantList(RestaurantList restaurants) {
        this.restaurantList = restaurants;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected ImageView restaurantImageView;
        protected TextView restaurantNameTextView;
        protected TextView restaurantStarsTextView;
        protected TextView restaurantDistanceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantImageView = itemView.findViewById(R.id.restaurant_image);
            restaurantNameTextView = itemView.findViewById(R.id.restaurant_name_text);
            restaurantStarsTextView = itemView.findViewById(R.id.restaurant_stars_text);
            restaurantDistanceTextView = itemView.findViewById(R.id.restaurant_relative_location_text);
        }
    }
}
