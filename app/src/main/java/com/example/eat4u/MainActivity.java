package com.example.eat4u;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.example.eat4u.backend.Backend;
import com.example.eat4u.backend.LocalBackend;
import com.example.eat4u.backend.db.DatabaseHelper;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {
    // Use this flag to forcefully reset the database and repopulating it with mock data
    private static boolean shouldResetDatabase = false;
    private static Logger LOGGER = Logger.getLogger(MainActivity.class.getSimpleName());

    private RecyclerView restaurantsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_editor);

        if (shouldResetDatabase) {
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... voids) {
                    DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this.getApplicationContext());
                    databaseHelper.reset();
                    return null;
                }
            }.execute();

            Backend backend = new LocalBackend(getApplicationContext());

            restaurantsRecyclerView = findViewById(R.id.restaurants_recycler_view);

        }
    }
}