package com.example.eat4u.model;

import java.util.ArrayList;
import java.util.Collection;

public class PhotoAlbum extends ArrayList<Photo> {

    public PhotoAlbum(Collection<Photo> photos) {
        super(photos);
    }

    public PhotoAlbum() {
        super();
    }

    public static PhotoAlbum empty() {
        return new PhotoAlbum();
    }
}
