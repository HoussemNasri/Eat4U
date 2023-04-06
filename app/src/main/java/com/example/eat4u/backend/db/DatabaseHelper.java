package com.example.eat4u.backend.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.eat4u.backend.db.entities.PhotoEntity;
import com.example.eat4u.backend.db.entities.RestaurantEntity;
import com.example.eat4u.backend.db.entities.ReviewEntity;
import com.example.eat4u.backend.db.entities.UserEntity;
import com.example.eat4u.backend.db.DatabaseContract.*;
import com.example.eat4u.exceptions.UnimplementedException;
import com.example.eat4u.model.Quality;
import com.example.eat4u.model.Review;
import com.example.eat4u.model.Stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final Logger LOGGER = Logger.getLogger(DatabaseHelper.class.getSimpleName());
    public static final String DATABASE_NAME = "eat4u.db";
    public static int DATABASE_VERSION = 8;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserEntry.SQL_CREATE_TABLE);
        db.execSQL(RestaurantEntry.SQL_CREATE_TABLE);
        db.execSQL(ReviewEntry.SQL_CREATE_TABLE);
        db.execSQL(PhotoEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ReviewEntry.DROP_TABLE);
        db.execSQL(UserEntry.SQL_DROP_TABLE);
        db.execSQL(RestaurantEntry.SQL_DROP_TABLE);
        db.execSQL(PhotoEntry.DROP_TABLE);
        onCreate(db);
    }

    public void storeRestaurant(RestaurantEntity restaurant) {
        ContentValues restaurantValues = new ContentValues();
        restaurantValues.put(RestaurantEntry._ID, restaurant.getId());
        restaurantValues.put(RestaurantEntry.COLUMN_NAME, restaurant.getName());
        restaurantValues.put(RestaurantEntry.COLUMN_ADDRESS, restaurant.getAddress());
        restaurantValues.put(RestaurantEntry.COLUMN_DESCRIPTION, restaurant.getDescription());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(RestaurantEntry.TABLE_NAME, null, restaurantValues);
    }

    public void storeRestaurants(RestaurantEntity... restaurants) {
        for (RestaurantEntity r : restaurants) {
            storeRestaurant(r);
        }
    }

    public List<RestaurantEntity> getAllRestaurants() {
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s", RestaurantEntry.TABLE_NAME), null);

        boolean isTableEmpty = !cursor.moveToFirst();
        if (isTableEmpty) {
            return Collections.emptyList();
        }

        List<RestaurantEntity> result = new ArrayList<>();
        result.add(readRestaurantRow(cursor));

        while (cursor.moveToNext()) {
            result.add(readRestaurantRow(cursor));
        }

        return result;
    }

    private RestaurantEntity readRestaurantRow(Cursor cursor) {
        return new RestaurantEntity(
                cursor.getLong(cursor.getColumnIndexOrThrow(RestaurantEntry._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndexOrThrow(RestaurantEntry.COLUMN_ADDRESS)));
    }

    public void storeUser(UserEntity user) {
        ContentValues values = new ContentValues();
        values.put(UserEntry._ID, user.getId());
        values.put(UserEntry.COLUMN_FIRSTNAME, user.getFirstname());
        values.put(UserEntry.COLUMN_LASTNAME, user.getLastname());
        values.put(UserEntry.COLUMN_EMAIL, user.getEmail());
        values.put(UserEntry.COLUMN_PHONE, user.getPhone());
        values.put(UserEntry.COLUMN_AVATAR_URL, user.getAvatar());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(UserEntry.TABLE_NAME, null, values);
    }

    public void storeUsers(UserEntity... users) {
        for (UserEntity u : users) {
            storeUser(u);
        }
    }

    public void storeReview(ReviewEntity review) {
        ContentValues values = new ContentValues();
        values.put(ReviewEntry.COLUMN_RESTAURANT_ID, review.getRestaurantId());
        values.put(ReviewEntry.COLUMN_RATER_ID, review.getRaterId());
        values.put(ReviewEntry.COLUMN_FOOD_QUALITY, review.getFoodQuality().toString());
        values.put(ReviewEntry.COLUMN_SERVICE_QUALITY, review.getServiceQuality().toString());
        values.put(ReviewEntry.COLUMN_AVERAGE_PRICE, review.getAveragePrice());
        values.put(ReviewEntry.COLUMN_STARS, review.getStars().toString());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(ReviewEntry.TABLE_NAME, null, values);
    }

    /**
     * Update or insert the supplied review
     */
    public void upsertReview(ReviewEntity review) {
        if (getReview(review.getRestaurantId(), review.getRaterId()).isPresent()) {
            getReadableDatabase().delete(ReviewEntry.TABLE_NAME, String.format("%s = ? AND %s = ?",
                            ReviewEntry.COLUMN_RESTAURANT_ID, ReviewEntry.COLUMN_RATER_ID),
                    new String[]{review.getRestaurantId().toString(), review.getRaterId().toString()});
        }
        storeReview(review);
    }

    public void storeReviews(ReviewEntity... reviews) {
        for (ReviewEntity rev : reviews) {
            storeReview(rev);
        }
    }

    public Optional<ReviewEntity> getReview(Long restaurantId, Long userId) {
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s WHERE %s = ? AND %s = ? LIMIT 1",
                        ReviewEntry.TABLE_NAME, ReviewEntry.COLUMN_RESTAURANT_ID, ReviewEntry.COLUMN_RATER_ID),
                new String[]{restaurantId.toString(), userId.toString()});

        boolean isEmpty = !cursor.moveToFirst();
        if (isEmpty) {
            return Optional.empty();
        }

        return Optional.of(readReviewRow(cursor));
    }

    private ReviewEntity readReviewRow(Cursor cursor) {
        return new ReviewEntity(
                Quality.parse(cursor.getString(cursor.getColumnIndexOrThrow(ReviewEntry.COLUMN_FOOD_QUALITY))),
                Quality.parse(cursor.getString(cursor.getColumnIndexOrThrow(ReviewEntry.COLUMN_SERVICE_QUALITY))),
                Stars.parse(cursor.getString(cursor.getColumnIndexOrThrow(ReviewEntry.COLUMN_STARS))),
                cursor.getDouble(cursor.getColumnIndexOrThrow(ReviewEntry.COLUMN_AVERAGE_PRICE)),
                cursor.getLong(cursor.getColumnIndexOrThrow(ReviewEntry.COLUMN_RATER_ID)),
                cursor.getLong(cursor.getColumnIndexOrThrow(ReviewEntry.COLUMN_RESTAURANT_ID))
        );
    }

    public int countRestaurantReviews(Long restaurantId) {
        // TODO: Implement properly
        return new Random().nextInt(3000);
    }

    public float computeRestaurantReviewsAverage(Long restaurantId) {
        return new Random().nextFloat() * 5;
    }

    private void storePhoto(PhotoEntity photo) {
        ContentValues values = new ContentValues();
        values.put(PhotoEntry._ID, photo.getId());
        values.put(PhotoEntry.COLUMN_URL, photo.getUrl());
        values.put(PhotoEntry.COLUMN_FOR_RESTAURANT, photo.getRestaurantId());

        getWritableDatabase().insert(PhotoEntry.TABLE_NAME, null, values);
    }

    private void storePhotos(PhotoEntity... photos) {
        for (PhotoEntity photo : photos) {
            storePhoto(photo);
        }
    }

    public List<PhotoEntity> getRestaurantPhotos(Long restaurantId) {
        Cursor cursor = getReadableDatabase().rawQuery(String.format("SELECT * FROM %s", PhotoEntry.TABLE_NAME), null);

        boolean isTableEmpty = !cursor.moveToFirst();
        if (isTableEmpty) {
            return Collections.emptyList();
        }

        List<PhotoEntity> result = new ArrayList<>();
        result.add(readPhotoEntityRow(cursor));

        while (cursor.moveToNext()) {
            result.add(readPhotoEntityRow(cursor));
        }

        return result;
    }

    private PhotoEntity readPhotoEntityRow(Cursor cursor) {
        return new PhotoEntity(
                cursor.getLong(cursor.getColumnIndexOrThrow(PhotoEntry._ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(PhotoEntry.COLUMN_URL)),
                cursor.getLong(cursor.getColumnIndexOrThrow(PhotoEntry.COLUMN_FOR_RESTAURANT)));
    }

    /**
     * Calling this method will clear the database and feed it mock data
     */
    public void reset() {
        onUpgrade(getWritableDatabase(), DATABASE_VERSION, DATABASE_VERSION + 1);
        DATABASE_VERSION++;

        populateRestaurants();
        populateUsers();
        populateReviews();
        populatePhotos();
    }

    private void populateRestaurants() {
        RestaurantEntity r1 = new RestaurantEntity(111L, "Sadrasa Kitchen and Bar", "Who’s hungry? Come take a seat at the Sadrasa Kitchen & Bar, an innovative space designed for savoring traditional recipes with a modern twist or relishing international favorites. Take your time exploring the flavors of a quick breakfast, a leisurely business lunch, or a celebratory dinner with friends. Whatever the occasion, whatever the time of day, Sadrasa Kitchen & Bar stands at the ready.",
                "Jl. Diponegoro No 27 Pullman Bandung Grand Central, Bandung 40115 Indonesia");
        RestaurantEntity r2 = new RestaurantEntity(222L, "Justus Steak House Metro Indah Mall", "",
                "Jl. Soekarno-hatta Jl. Mtc Barat No.59040286, Bandung 40286 Indonesia");
        RestaurantEntity r3 = new RestaurantEntity(333L, "Pago Restaurant", "",
                "Jl. Gatot Subroto No.83 The Papandayan Hotel, Bandung 40262 Indonesia");
        RestaurantEntity r4 = new RestaurantEntity(444L, "Justus Steak House Dago", "",
                "Jl. Ir. H. Juanda No. 59 Tamansari, Bandung Wetan, Bandung 40116 Indonesia");
        RestaurantEntity r5 = new RestaurantEntity(555L, "Hummingbird Eatery & Space", "We offer you luxurious tasting food and beverages. Celebrate special and cozy moments with us and enjoy the outstanding interior of our restaurant.",
                "Jl. Progo 14, Bandung 40115 Indonesia");
        RestaurantEntity r6 = new RestaurantEntity(666L, "Purnawarman Restaurant",
                "Experience fresh flavors from Asia and around the world in the all-day dining venue, Purnawarman Restaurant at the Hilton Bandung hotel. Savor a fresh buffet breakfast, a la carte lunch and buffet dinner and experience the theatrical, interactive kitchen. Dine on contemporary and traditional Asian delicacies such as Sop Buntut and Purnawarman Fried Rice on relaxed indoor and outdoor seating in this warm and welcoming restaurant.",
                "Jl. HOS Tjokroaminoto no. 41-43 Hilton Hotel Bandung, Bandung 40172 Indonesia");
        RestaurantEntity r7 = new RestaurantEntity(777L, "Miss Bee Providore", "",
                "Jl. Ranca Bentang no. 11A Ciumbuleuit, Bandung 40142 Indonesia");

        storeRestaurants(r1, r2, r3, r4, r5, r6, r7);
    }

    private void populateUsers() {
        UserEntity u1 = new UserEntity(1L, "Bernfried", "Schock", "bernfried.schock@example.com",
                "0859-6994128", "https://randomuser.me/api/portraits/men/49.jpg");
        UserEntity u2 = new UserEntity(2L, "Youri", "Ho", "youri.ho@example.com",
                "(015) 7642350", "https://randomuser.me/api/portraits/men/12.jpg");
        UserEntity u3 = new UserEntity(3L, "Oscar", "Bonnet", "oscar.bonnet@example.com",
                "01-41-94-30-95", "https://randomuser.me/api/portraits/men/94.jpg");

        storeUsers(u1, u2, u3);
    }

    private void populateReviews() {
        ReviewEntity rev1 = new ReviewEntity(Quality.AVERAGE, Quality.GOOD, Stars.ONE, 13.2d, 1L, 111L);
        ReviewEntity rev2 = new ReviewEntity(Quality.GOOD, Quality.EXCELLENT, Stars.THREE, 14.9d, 2L, 111L);
        ReviewEntity rev3 = new ReviewEntity(Quality.POOR, Quality.AVERAGE, Stars.ONE, 14.1d, 3L, 111L);

        storeReviews(rev1, rev2, rev3);
    }

    private void populatePhotos() {
        PhotoEntity p1 = new PhotoEntity(1111L, "https://media-cdn.tripadvisor.com/media/photo-o/1c/e7/29/39/breakfast-corner.jpg", 111L);
        PhotoEntity p2 = new PhotoEntity(2222L, "https://media-cdn.tripadvisor.com/media/photo-m/1280/24/8a/64/1c/ambience.jpg", 222L);
        PhotoEntity p3 = new PhotoEntity(3333L, "https://media-cdn.tripadvisor.com/media/photo-o/15/7a/f4/02/pago-balcony.jpg", 333L);
        PhotoEntity p4 = new PhotoEntity(4444L, "https://media-cdn.tripadvisor.com/media/photo-o/15/7a/f4/02/pago-balcony.jpg", 111L);

        storePhotos(p1, p2, p3, p4);
    }


}
