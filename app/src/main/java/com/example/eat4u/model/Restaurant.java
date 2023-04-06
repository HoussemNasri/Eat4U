package com.example.eat4u.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Restaurant implements Parcelable {
    private final Long id;
    private final String name;
    private final String address;
    private final Location exactLocation;
    private final float reviewsCount;
    private final float reviewsAverage;
    private final PhotoAlbum photoAlbum;
    private final String description;

    public Restaurant(Long id, String name, String address, String description, Location exactLocation, float reviewsCount, float reviewsAverage, PhotoAlbum photoAlbum) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.exactLocation = exactLocation;
        this.reviewsCount = reviewsCount;
        this.reviewsAverage = reviewsAverage;
        this.photoAlbum = photoAlbum;
    }

    public Restaurant(Long id, String name, String address, String description) {
        this(id, name, address, description, null, 0, 0.0f, PhotoAlbum.empty());
    }

    protected Restaurant(Parcel in) {
        id = in.readLong();
        name = in.readString();
        address = in.readString();
        reviewsCount = in.readFloat();
        reviewsAverage = in.readFloat();
        description = in.readString();
        List<Photo> photos = new ArrayList<>();
        in.readTypedList(photos, Photo.CREATOR);
        photoAlbum = PhotoAlbum.wrap(photos);

        exactLocation = null;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }

    public Optional<Location> getExactLocation() {
        return Optional.ofNullable(exactLocation);
    }

    public float getReviewsCount() {
        return reviewsCount;
    }

    public float getReviewsAverage() {
        return reviewsAverage;
    }

    public PhotoAlbum getPhotoAlbum() {
        return photoAlbum;
    }

    public Optional<Photo> getThumbnail() {
        if (!getPhotoAlbum().isEmpty()) {
            return Optional.of(getPhotoAlbum().get(new Random().nextInt(getPhotoAlbum().size())));
        }
        return Optional.empty();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeLong(-1);
        } else {
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(address);
        dest.writeFloat(reviewsCount);
        dest.writeFloat(reviewsAverage);
        dest.writeString(description);
        dest.writeTypedList(photoAlbum);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Restaurant> CREATOR = new Creator<>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

}

