package com.example.eat4u.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private final Long id;
    private final String name;
    private final String address;

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Restaurant(String name, String address) {
        this(null, name, address);
    }

    protected Restaurant(Parcel in) {
        id = in.readLong();
        name = in.readString();
        address = in.readString();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeLong(-1);
        } else {
            dest.writeLong(id);
        }
        dest.writeString(name);
        dest.writeString(address);
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

