package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addTasks extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);

        EditText taskTitle = findViewById(R.id.titleTask);
        EditText taskBody = findViewById(R.id.bodyTask);
        EditText taskState = findViewById(R.id.stateTask);
        Button button = findViewById(R.id.button2);
        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext() , "Submitted!" , Toast.LENGTH_LONG).show();
                Task task = new Task(taskTitle.getText().toString(),taskBody.getText().toString(),taskState.getText().toString());
                TaskDataBase db = Room.databaseBuilder(getApplicationContext(),
                        TaskDataBase.class, "task").allowMainThreadQueries().build();
                TaskDAO taskDAO = db.taskDAO();
                taskDAO.insertAll(task);
                Intent gotToHome = new Intent(addTasks.this , MainActivity.class);
                startActivity(gotToHome);
            }
        }));
    }
}