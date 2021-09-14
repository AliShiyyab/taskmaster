package com.example.taskmaster;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.FileUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.taskmaster;
import com.amplifyframework.datastore.generated.model.team;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

public class addTasks extends AppCompatActivity {
    public List list = new ArrayList();
    public List<team> teams = new ArrayList<>();
    team selectedTeam;
    private String name;
    private String[] teamsNames;
    private Handler toastHandler;
    private EditText taskTitle;
    private EditText taskBody;
    private EditText taskState;
    private Button button;
    private Button UploadFile;
//    String uploadedFileName;
    Uri uri;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        taskTitle = findViewById(R.id.titleTask);
        taskBody = findViewById(R.id.bodyTask);
        taskState = findViewById(R.id.stateTask);
        button = findViewById(R.id.button4);
        UploadFile = findViewById(R.id.UploadFile);

        Amplify.API.query(ModelQuery.list(team.class), response -> {
            for (team team1 : response.getData()) {
                teams.add(team1);
                Log.i("Teams: ", team1.getName());
            }
        }, error -> Log.e("MyAmplifyApp", "Query failure", error));

        UploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "WOOOOW!", Toast.LENGTH_LONG).show();
                getFileFromDevice();
            }
        });

        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadInputStream();
                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();
                RadioGroup radioGroup = findViewById(R.id.radioGroub);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                for (team teamm : teams) {
                    if (teamm.getName().equals(radioButton.getText().toString())) {
                        selectedTeam = teamm;
                    }
                }
                taskmaster todo = taskmaster.builder()
                        .title(taskTitle.getText().toString())
                        .body(taskBody.getText().toString())
                        .state(taskState.getText().toString())
                        .teams(selectedTeam)
                        .build();
                Amplify.API.mutate(
                        ModelMutation.create(todo),
                        response -> Log.i("MyAmplifyApp", "Added Todo with id: " + response.getData().getId()),
                        error -> Log.e("MyAmplifyApp", "Create failed", error)
                );
            }
        }));

    }

    private void uploadInputStream() {
        if (uri != null) {
            try {
                InputStream exampleInputStream = getContentResolver().openInputStream(uri);
                Amplify.Storage.uploadInputStream(
                        taskTitle.getText().toString(),
                        exampleInputStream,
                        result -> Log.i("MyAmplifyApp", "Successfully uploaded: " + result.getKey()),
                        storageFailure -> Log.e("MyAmplifyApp", "Upload failed", storageFailure)
                );
            } catch (FileNotFoundException error) {
                Log.e("MyAmplifyApp", "Could not find file to open for input stream.", error);
            }
        }
    }

    private void getFileFromDevice() {
        Intent chooseFile = new Intent(Intent.ACTION_GET_CONTENT);
        chooseFile.setType("*/*");
        chooseFile = Intent.createChooser(chooseFile, "Choose File!");
        startActivityForResult(chooseFile, 2048);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        File file = new File(getApplicationContext().getFilesDir(), "uploadFileCopied");
        uri = data.getData();
    }
}