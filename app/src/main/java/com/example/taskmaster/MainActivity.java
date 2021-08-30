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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the element by id
        //JS
        //declare button
        Button firstButton = findViewById(R.id.button3);
        Button secondButton = findViewById(R.id.button4);
        //onclick
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "To Add Tasks!" , Toast.LENGTH_LONG).show();
                Intent goToAddTask = new Intent(MainActivity.this , addTasks.class);
                startActivity(goToAddTask);
            }
        });
        secondButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "To All Tasks!" , Toast.LENGTH_LONG).show();
                Intent goToAllTask = new Intent(MainActivity.this , allTasks.class);
                startActivity(goToAllTask);
            }
        }));

        //Decalre the new button
        Button level2 = findViewById(R.id.levelTow);
        Button level3 = findViewById(R.id.levelThree);
        Button level4 = findViewById(R.id.levelFour);

        level2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLevelTow = new Intent(MainActivity.this , Details.class);
                String headerValue = "201";
                goToLevelTow.putExtra("Header",headerValue);
                startActivity(goToLevelTow);
            }
        }));
        level3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLevelThree = new Intent(MainActivity.this , Details.class);
                String headerValue = "301";
                goToLevelThree.putExtra("Header",headerValue);
                startActivity(goToLevelThree);
            }
        }));
        level4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLevelFour = new Intent(MainActivity.this , Details.class);
                String headerValue = "401";
                goToLevelFour.putExtra("Header",headerValue);
                startActivity(goToLevelFour);
            }
        }));
        Button setiing = findViewById(R.id.setiing);
        setiing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "To Setting!" , Toast.LENGTH_LONG).show();
                Intent gotToSetting = new Intent(MainActivity.this , Setting.class);
                startActivity(gotToSetting);
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName", "Ali");

        TextView userNameView = findViewById(R.id.homeUserName);
        userNameView.setText(userName + " tasks");
    }
}