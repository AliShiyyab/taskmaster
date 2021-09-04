package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String taskTitle = intent.getExtras().getString("titleTaskDetails");
        String taskBody = intent.getExtras().getString("bodyTaskDetails");
        String taskState = intent.getExtras().getString("stateTaskDetails");

        TextView taskTitleView = findViewById(R.id.detailHead);
        TextView taskBodyView = findViewById(R.id.detaildDescribe);
        TextView taskStateView = findViewById(R.id.States);

        taskTitleView.setText(taskTitle);
        taskBodyView.setText(taskBody);
        taskStateView.setText(taskState);
    }
}