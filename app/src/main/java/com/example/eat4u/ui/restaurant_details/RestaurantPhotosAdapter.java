package com.example.eat4u.ui.restaurant_details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.example.eat4u.R;
import com.example.eat4u.model.Photo;
import com.example.eat4u.model.PhotoAlbum;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class RestaurantPhotosAdapter extends SliderViewAdapter<RestaurantPhotosAdapter.ViewHolder> {
    private final PhotoAlbum photoAlbum;
    private final Context context;

    public RestaurantPhotosAdapter(PhotoAlbum photoAlbum, Context context) {
        this.photoAlbum = photoAlbum;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_photo_item, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Photo photo = photoAlbum.get(position);

        Glide.with(context)
                .load(photo.getUrl())
                .into(viewHolder.restaurantImageView);
    }

    @Override
    public int getCount() {
        System.out.println("Size = " + photoAlbum.size());
        photoAlbum.forEach(p -> System.out.println(p.getUrl()));
        return photoAlbum.size();
    }

    public static class ViewHolder extends SliderViewAdapter.ViewHolder {
        private final ImageView restaurantImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            restaurantImageView = itemView.findViewById(R.id.restaurant_photo_image);
        }
    }
}
