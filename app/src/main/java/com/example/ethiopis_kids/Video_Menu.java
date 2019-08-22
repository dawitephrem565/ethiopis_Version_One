package com.example.ethiopis_kids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

public class Video_Menu extends AppCompatActivity implements View.OnClickListener{
 CardView reading_card , music_card , funny_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed__in);
        reading_card=findViewById(R.id.reading_video_card1);
        music_card=findViewById(R.id.music_video_card1);
        funny_card=findViewById(R.id.funny_video_card1);

        reading_card.setOnClickListener(this);
        music_card.setOnClickListener(this);
        funny_card.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.reading_video_card1:
             startActivity(new Intent(Video_Menu.this,Video_activity.class));
             break;
            case R.id.music_video_card1:
                startActivity(new Intent(Video_Menu.this,Music_Video_activity.class));
                break;

            case R.id.funny_video_card1:
                startActivity(new Intent(Video_Menu.this,Funny_Video_Activity.class));
                break;
        }
    }
}
