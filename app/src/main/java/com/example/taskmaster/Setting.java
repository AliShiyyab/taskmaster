package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button btn = findViewById(R.id.button5);

        btn.setOnClickListener((View) -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Setting.this);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

            EditText userNameField = findViewById(R.id.editTextTextPersonName);
            String userName = userNameField.getText().toString();

            sharedPreferencesEditor.putString("userName", userName);
            sharedPreferencesEditor.apply();

            //Intent to MainActivity when clicked!
            Intent goToMain = new Intent(Setting.this , MainActivity.class);
            startActivity(goToMain);
        });
    }
}