package com.example.taskmaster;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigator;

import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.taskmaster;
import com.amplifyframework.datastore.generated.model.team;

import java.util.ArrayList;
import java.util.List;

import android.os.Handler;

import java.util.stream.Collectors;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tasks);
        taskTitle = findViewById(R.id.titleTask);
        taskBody = findViewById(R.id.bodyTask);
        taskState = findViewById(R.id.stateTask);
        button = findViewById(R.id.button2);


        Amplify.API.query(ModelQuery.list(team.class), response -> {
            for (team team1 : response.getData()) {
                teams.add(team1);
                Log.i("Teams: ", team1.getName());
            }
        }, error -> Log.e("MyAmplifyApp", "Query failure", error));


        button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Submitted!", Toast.LENGTH_LONG).show();
                RadioGroup radioGroup = findViewById(R.id.radioGroub);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                for (team teamm : teams) {
                    if (teamm.getName().equals(radioButton.getText().toString())){
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
                list.add(todo);
            }
        }));
    }
}