package com.example.ethiopis_kids;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity implements View.OnClickListener
{
    private FirebaseAnalytics mFirebaseAnalytics;
    ImageView avater , setting;
    TextView username;
    ImageView profile_page;
    Button reading_time , video_time ,role_model,News;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reading_time = findViewById(R.id.Reading_Time_Start_button);
        username = findViewById(R.id.User_Name_Main_Page);
        video_time = findViewById(R.id.video_start_button);
        role_model =findViewById(R.id.Role_model_start_btn);
        News = findViewById(R.id.News_start_btn);
        profile_page = findViewById(R.id.profile_image);
        setting=findViewById(R.id.setting_btn);
        Fabric.with(this, new Crashlytics());
       // Glide.with(this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile_page);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
       ReadSingleContact();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        reading_time.setOnClickListener(this);
        video_time.setOnClickListener(this);
        role_model.setOnClickListener(this);
        setting.setOnClickListener(this);
      profile_page.setOnClickListener(this);
      News.setOnClickListener(this);
    }

    private void ReadSingleContact() {
        DocumentReference user = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    username.setText(doc.get("username").toString());

                    if(doc.get("Profile_Image")!=null){
                        Glide.with(MainActivity.this).load(doc.get("Profile_Image").toString()).apply(RequestOptions.circleCropTransform()).into(profile_page);

                    }
                    else if(doc.get("Profile_Image")=="")
                    {
                        Glide.with(MainActivity.this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile_page);

                    }
                    else
                    {
                        Glide.with(MainActivity.this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile_page);

                    }
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.Reading_Time_Start_button:

                Intent intent = new Intent(MainActivity.this,Media_Player.class);
                startActivity(intent);
                break;
            case R.id.video_start_button:
     Intent video_intent = new Intent(MainActivity.this, Video_Menu.class);
     startActivity(video_intent);
                break;
            case R.id.Role_model_start_btn:
                Intent role_intent = new Intent(MainActivity.this,Role_Model.class);
                startActivity(role_intent);
                break;
            case R.id.News_start_btn:

                startActivity( new Intent(MainActivity.this,Ethiopis_News.class));
                break;
            case R.id.profile_image:
                startActivity(new Intent(MainActivity.this,Profile_Page.class));
                break;
            case R.id.setting_btn:
                startActivity(new Intent(MainActivity.this,Setting.class));
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
