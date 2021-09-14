package com.example.taskmaster;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.amplifyframework.datastore.generated.model.team;

import java.util.ArrayList;
import java.util.List;

public class Setting extends AppCompatActivity {
    public String teams = "Null";
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        RadioGroup radioGroup = findViewById(R.id.radioGroupBox);



        //----------------------------------------------------------
        Button btn = findViewById(R.id.button5);
        btn.setOnClickListener((View) -> {
            RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
            teams = radioButton.getText().toString();
            System.out.println("Team name " + teams);



            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Setting.this);
            SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();

            EditText userNameField = findViewById(R.id.editTextTextPersonName);
            String userName = userNameField.getText().toString();

            sharedPreferencesEditor.putString("userName", userName);
            sharedPreferencesEditor.putString("Team", teams);
            sharedPreferencesEditor.apply();

            //Intent to MainActivity when clicked!
            Intent goToMain = new Intent(Setting.this , MainActivity.class);
            startActivity(goToMain);
        });
    }
}