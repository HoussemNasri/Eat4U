package com.example.eat4u.model;

import java.util.ArrayList;

public class PhotoAlbum extends ArrayList<Photo> {

    public static PhotoAlbum empty() {
        return new PhotoAlbum();
    }
}
