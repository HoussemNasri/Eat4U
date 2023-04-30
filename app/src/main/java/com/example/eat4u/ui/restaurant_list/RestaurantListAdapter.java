package com.example.eat4u.ui.restaurant_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eat4u.R;
import com.example.eat4u.model.Restaurant;
import com.example.eat4u.model.RestaurantList;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class RestaurantListAdapter extends RecyclerView.Adapter<RestaurantListAdapter.ViewHolder> {

    private final Context context;
    private RestaurantList restaurantList;
    private RestaurantItemClickedListener restaurantItemClickedListener;

    public RestaurantListAdapter(Context context, @NotNull RestaurantList restaurantList, RestaurantItemClickedListener restaurantItemClickedListener) {
        this.context = context;
        this.restaurantList = Objects.requireNonNull(restaurantList);
        this.restaurantItemClickedListener = restaurantItemClickedListener;
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

        if (restaurant.getId() != null) {
            holder.restaurantIdTextView.setText(restaurant.getId().toString());
        }

        holder.restaurantTitleTextView.setText(restaurant.getName());
        holder.restaurantAvgPriceTextView.setText(restaurant.getAveragePrice()+"$");

        holder.itemView.setOnClickListener(e -> {
            if (restaurantItemClickedListener != null) {
                restaurantItemClickedListener.onRestaurantItemClicked(position);
            }
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

        private TextView restaurantIdTextView;
        private TextView restaurantTitleTextView;
        private TextView restaurantAvgPriceTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            restaurantIdTextView = itemView.findViewById(R.id.tv_restaurant_id);
            restaurantTitleTextView = itemView.findViewById(R.id.tv_restaurant_title);
            restaurantAvgPriceTextView = itemView.findViewById(R.id.tv_restaurant_avg_price);
        }
    }

    @FunctionalInterface
    public interface RestaurantItemClickedListener {
        void onRestaurantItemClicked(int position);
    }
}
