package com.example.ethiopis_kids;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class Video_Play_Screen extends AppCompatActivity {
WebView screen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video__play__screen);
        screen=(WebView) findViewById(R.id.play_video_screen);
      String link =   getIntent().getStringExtra("play");
       screen.loadUrl("www.youtube.com/embed/eWEF1Zrmdow\" frameborder=\"0\" allowfullscreen></iframe>");
        screen.getSettings().setJavaScriptEnabled(true);
        screen.setWebChromeClient(new WebChromeClient(){

        });
    }
}
