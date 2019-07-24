package com.example.ethiopis_kids;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAnalytics mFirebaseAnalytics;
    Button reading_time , video_time ,role_model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reading_time = findViewById(R.id.Reading_Time_Start_button);
        video_time = findViewById(R.id.video_start_button);
        role_model =findViewById(R.id.Role_model_start_btn);
        Fabric.with(this, new Crashlytics());
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        reading_time.setOnClickListener(this);
        video_time.setOnClickListener(this);
        role_model.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Reading_Time_Start_button:

                Intent intent = new Intent(MainActivity.this,Reading_Time_Activity.class);
                startActivity(intent);
                break;
            case R.id.video_start_button:
     Intent video_intent = new Intent(MainActivity.this,Video_activity.class);
     startActivity(video_intent);
                break;
            case R.id.Role_model_start_btn:
                Intent role_intent = new Intent(MainActivity.this,Role_Model.class);
                startActivity(role_intent);
                break;
        }
    }
}
