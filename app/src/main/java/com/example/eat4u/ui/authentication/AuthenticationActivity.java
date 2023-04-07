package com.example.eat4u.ui.authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.eat4u.R;
import com.example.eat4u.ui.review_editor.ReviewEditorActivity;
import com.example.eat4u.utils.Globals;
import com.google.android.material.textfield.TextInputEditText;

public class AuthenticationActivity extends AppCompatActivity {
    private TextInputEditText firstnameEditText;
    private TextInputEditText lastnameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authetication);

        this.firstnameEditText = findViewById(R.id.firstname_edittext);
        this.lastnameEditText = findViewById(R.id.lastname_edittext);

    }

    public void onLoginClicked(View view) {
        Intent goToReviewEditorIntent = new Intent(this, ReviewEditorActivity.class);
        goToReviewEditorIntent.putExtra(Globals.USER_FIRSTNAME_EXTRA, firstnameEditText.getText().toString());
        goToReviewEditorIntent.putExtra(Globals.USER_LASTNAME_EXTRA, lastnameEditText.getText().toString());
        finish();
        startActivity(goToReviewEditorIntent);
    }
}