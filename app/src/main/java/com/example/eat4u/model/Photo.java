package com.example.eat4u.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Photo implements Parcelable {
    private final Long id;
    private final String url;

    public Photo(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    protected Photo(Parcel in) {
        id = in.readLong();
        url = in.readString();
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public static final Creator<Photo> CREATOR = new Creator<>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(url);
    }
}
