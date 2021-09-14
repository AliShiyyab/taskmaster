package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.amplifyframework.core.Amplify;

import java.io.File;

public class Details extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        String taskTitle = intent.getExtras().getString("TaskName");
        String taskBody = intent.getExtras().getString("TaskBody");
        String taskState = intent.getExtras().getString("TaskState");

        TextView taskTitleView = findViewById(R.id.detailHead);
        TextView taskBodyView = findViewById(R.id.detaildDescribe);
        TextView taskStateView = findViewById(R.id.States);
        ImageView detailsImage = findViewById(R.id.detailsImage);

        taskTitleView.setText(taskTitle);
        taskBodyView.setText(taskBody);
        taskStateView.setText(taskState);
//        detailsImage
        Amplify.Storage.downloadFile(
                taskTitle,
                new File(getApplicationContext().getFilesDir() + "/Example Key.jpg"),
                result ->{
                    detailsImage.setImageBitmap(BitmapFactory.decodeFile(result.getFile().getPath()));
                    Log.i("MyAmplifyApp", "Successfully downloaded: " + result.getFile().getName());
                },
                error -> Log.e("MyAmplifyApp",  "Download Failure", error)
        );
    }
}