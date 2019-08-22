package com.example.ethiopis_kids;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.crashlytics.android.Crashlytics;

import java.io.IOException;

import io.fabric.sdk.android.Fabric;

public class reading_detaile extends Activity implements View.OnClickListener {
    TextView book_title , book_story;
    ImageView book_cover;
    FloatingActionButton play,pause;
    MediaPlayer media;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_detail);
        Fabric.with(this, new Crashlytics());
        book_title = (TextView)findViewById(R.id.title_viewactivity);
        book_cover = (ImageView)findViewById(R.id.profileimage);
        book_story = (TextView)findViewById(R.id.detail);
        play=findViewById(R.id.sound_play);
        pause=findViewById(R.id.sound_pause);

        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.avater).error(R.drawable.common_google_signin_btn_icon_dark);

        String image_url = getIntent().getExtras().getString("book_cover");
        // set image using Glide
        Glide.with(reading_detaile.this).load(image_url).apply(requestOptions).into(book_cover);
        //String image_url = getIntent().getExtras().getString("anime_img") ;
        book_title.setText(getIntent().getStringExtra("book_title"));
        book_story.setText(getIntent().getStringExtra("book_story"));
        play.setOnClickListener(this);
        pause.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sound_play:

             media = new MediaPlayer();
                try {
                    media.setDataSource("https://firebasestorage.googleapis.com/v0/b/ethiopiskids-f6a03.appspot.com/o/voice%2Fvoice.ogg?alt=media&token=b41273fc-c4e2-47b8-a531-eef9becd0d57");
                    media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @SuppressLint("RestrictedApi")
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            try {
                                mp.start();
                                pause.setVisibility(View.VISIBLE);
                                play.setVisibility(View.INVISIBLE);
                            } catch (Exception e) {
                                Toast.makeText(reading_detaile.this, "No Connection", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                    media.prepare();
                } catch (IOException e) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sound_pause:
                 media = new MediaPlayer();
                try {
                    //media_pause.setDataSource("https://firebasestorage.googleapis.com/v0/b/ethiopiskids-f6a03.appspot.com/o/voice%2Fvoice.ogg?alt=media&token=b41273fc-c4e2-47b8-a531-eef9becd0d57");
                    media.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            try {
                                mp.stop();
                            } catch (Exception e) {
                                Toast.makeText(reading_detaile.this, "No Connection", Toast.LENGTH_SHORT).show();
                            }
                        }


                    });
                    media.prepare();
                } catch (IOException e) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                }

                break;


        }
    }




    }

