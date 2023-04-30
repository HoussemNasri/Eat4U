package com.example.eat4u.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private final Long id;
    private String name;
    private String address;
    private Quality foodQuality;
    private Quality serviceQuality;
    private Stars stars;
    private Double averagePrice;

    public Restaurant(Long id, String name, String address, Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.foodQuality = foodQuality;
        this.serviceQuality = serviceQuality;
        this.stars = stars;
        this.averagePrice = averagePrice;
    }

    public Restaurant(String name, String address, Quality foodQuality, Quality serviceQuality, Stars stars, Double averagePrice) {
        this(null, name, address, foodQuality, serviceQuality, stars, averagePrice);
    }

    protected Restaurant(Parcel in) {
        id = in.readLong();
        name = in.readString();
        address = in.readString();
        foodQuality = Quality.parse(in.readString());
        serviceQuality = Quality.parse(in.readString());
        stars = Stars.parse(in.readString());
        averagePrice = in.readDouble();
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

    public Quality getFoodQuality() {
        return foodQuality;
    }

    public Quality getServiceQuality() {
        return serviceQuality;
    }

    public Stars getStars() {
        return stars;
    }

    public Double getAveragePrice() {
        return averagePrice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFoodQuality(Quality foodQuality) {
        this.foodQuality = foodQuality;
    }

    public void setServiceQuality(Quality serviceQuality) {
        this.serviceQuality = serviceQuality;
    }

    public void setStars(Stars stars) {
        this.stars = stars;
    }

    public void setAveragePrice(Double averagePrice) {
        this.averagePrice = averagePrice;
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
        dest.writeString(foodQuality.name());
        dest.writeString(serviceQuality.name());
        dest.writeString(stars.name());
        dest.writeDouble(averagePrice);
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

