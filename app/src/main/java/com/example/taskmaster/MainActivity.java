package com.example.taskmaster;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v4.content.LocalBroadcastManager;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.Callback;
import com.amazonaws.mobile.client.UserStateDetails;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointConfiguration;
import com.amazonaws.mobileconnectors.pinpoint.PinpointManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.aws.AWSApiPlugin;
import com.amplifyframework.api.graphql.model.ModelQuery;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.AWSDataStorePlugin;
import com.amplifyframework.datastore.generated.model.taskmaster;
import com.amplifyframework.storage.s3.AWSS3StoragePlugin;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    private static PinpointManager pinpointManager;

    public static PinpointManager getPinpointManager(final Context applicationContext) {
        if (pinpointManager == null) {
            final AWSConfiguration awsConfig = new AWSConfiguration(applicationContext);
            AWSMobileClient.getInstance().initialize(applicationContext, awsConfig, new Callback<UserStateDetails>() {
                @Override
                public void onResult(UserStateDetails userStateDetails) {
                    Log.i("INIT", String.valueOf(userStateDetails.getUserState()));
                }

                @Override
                public void onError(Exception e) {
                    Log.e("INIT", "Initialization error.", e);
                }
            });

            PinpointConfiguration pinpointConfig = new PinpointConfiguration(
                    applicationContext,
                    AWSMobileClient.getInstance(),
                    awsConfig);

            pinpointManager = new PinpointManager(pinpointConfig);

            FirebaseMessaging.getInstance().getToken()
                    .addOnCompleteListener(new OnCompleteListener<String>() {
                        @Override
                        public void onComplete(@NonNull com.google.android.gms.tasks.Task<String> task) {
                            if (!task.isSuccessful()) {
                                Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                                return;
                            }
                            final String token = task.getResult();
                            Log.d(TAG, "Registering push notifications token: " + token);
                            pinpointManager.getNotificationClient().registerDeviceToken(token);
                        }
                    });
        }
        return pinpointManager;
    }

    TaskDAO taskDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // Add these lines to add the AWSApiPlugin plugins
            Amplify.addPlugin(new AWSApiPlugin());
            Amplify.addPlugin(new AWSCognitoAuthPlugin());
            Amplify.addPlugin(new AWSS3StoragePlugin());
            Amplify.configure(getApplicationContext());
            getPinpointManager(getApplicationContext());
//            Amplify.addPlugin(new AWSDataStorePlugin());
            Log.i("MyAmplifyApp", "Initialized Amplify");
            Amplify.Auth.signInWithWebUI(
                    this,
                    result -> Log.i("AuthQuickStart", result.toString()),
                    error -> Log.e("AuthQuickStart", error.toString())
            );
        } catch (
                AmplifyException error) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", error);
        }

        //getting the element by id
        //JS
        //declare button
        Button firstButton = findViewById(R.id.button3);
        Button secondButton = findViewById(R.id.SignOut);
        //onclick
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "To Add Tasks!", Toast.LENGTH_LONG).show();
                Intent goToAddTask = new Intent(MainActivity.this, addTasks.class);
                startActivity(goToAddTask);
            }
        });
        secondButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "To All Tasks!", Toast.LENGTH_LONG).show();
                Intent goToAllTask = new Intent(MainActivity.this, allTasks.class);
                startActivity(goToAllTask);
            }
        }));

        //Decalre the new button
        Button level2 = findViewById(R.id.levelTow);
        Button level3 = findViewById(R.id.levelThree);
        Button level4 = findViewById(R.id.levelFour);
        Button SignOut = findViewById(R.id.SignOut);

        level2.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLevelTow = new Intent(MainActivity.this, Details.class);
                String headerValue = "201";
                goToLevelTow.putExtra("Header", headerValue);
                startActivity(goToLevelTow);
            }
        }));
        level3.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLevelThree = new Intent(MainActivity.this, Details.class);
                String headerValue = "301";
                goToLevelThree.putExtra("Header", headerValue);
                startActivity(goToLevelThree);
            }
        }));
        SignOut.setOnClickListener((new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Amplify.Auth.signOut(
                                () -> Log.i("AuthQuickstart", "Signed out successfully"),
                                error -> Log.e("AuthQuickstart", error.toString())
                        );
                    }
                }));

        level4.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToLevelFour = new Intent(MainActivity.this, Details.class);
                String headerValue = "401";
                goToLevelFour.putExtra("Header", headerValue);
                startActivity(goToLevelFour);
            }
        }));
        Button setiing = findViewById(R.id.setiing);
        setiing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "To Setting!", Toast.LENGTH_LONG).show();
                Intent gotToSetting = new Intent(MainActivity.this, Setting.class);
                startActivity(gotToSetting);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView recuclerView = findViewById(R.id.taskRecucleView);
        Handler handler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                recuclerView.getAdapter().notifyDataSetChanged();
                return false;
            }
        });
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String userName = sharedPreferences.getString("userName", "Ali");
        String teamIdFromSettings = sharedPreferences.getString("Team", "");

        System.out.println(teamIdFromSettings);

        TextView userNameView = findViewById(R.id.homeUserName);
        userNameView.setText(userName + " tasks");
        ArrayList<taskmaster> arrayList = new ArrayList<taskmaster>();
        ArrayList<taskmaster> arrayList1 = new ArrayList<taskmaster>();
        recuclerView.setLayoutManager(new LinearLayoutManager(this));
        recuclerView.setAdapter(new ViewAdapter(arrayList1));
//        recuclerView.setAdapter(new ViewAdapter(arrayList1));

        Amplify.API.query(ModelQuery.list(taskmaster.class), response -> {
            for (taskmaster tasksss : response.getData()) {
                Log.i("MyAmplifyApp", tasksss.getTitle());
                Log.i("MyAmplifyApp", tasksss.getBody());
                Log.i("MyAmplifyApp", tasksss.getState());
                arrayList.add(tasksss);
            }
            for (int i = 0 ; i < arrayList.size(); i++){
                if (arrayList.get(i).getTeams().getName().equals(teamIdFromSettings)){
                    arrayList1.add(arrayList.get(i));
                }
            }
            handler.sendEmptyMessage(1);
            Log.i("MyAmplifyApp", "Out of Loop!");
        }, error -> Log.e("MyAmplifyApp", "Query failure", error));
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}