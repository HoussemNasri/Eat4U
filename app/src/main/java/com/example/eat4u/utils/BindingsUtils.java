package com.example.eat4u.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.lifecycle.LiveData;

public class BindingsUtils {

    public static void onTextChanged(EditText editText, TextChangeListener textChangeListener) {
        editText.addTextChangedListener(new TextWatcher() {
            String oldValue;
            String newValue;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldValue = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                newValue = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {
                textChangeListener.onTextChanged(oldValue, newValue);
            }
        });
    }

    public interface TextChangeListener {
        void onTextChanged(String oldValue, String newValue);
    }
}
