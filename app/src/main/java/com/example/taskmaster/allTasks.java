package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class allTasks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        Button button = findViewById(R.id.button);

        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "To Main Activity Tasks!" , Toast.LENGTH_LONG).show();
                Intent goToActivity = new Intent(allTasks.this , MainActivity.class);
                startActivity(goToActivity);
            }
        }));
    }
}