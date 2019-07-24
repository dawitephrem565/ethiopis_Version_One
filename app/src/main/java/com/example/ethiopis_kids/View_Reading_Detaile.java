package com.example.ethiopis_kids;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

public class View_Reading_Detaile extends Activity {
 TextView book_title , book_story;
 ImageView book_cover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__reading__detaile);
        Fabric.with(this, new Crashlytics());
        book_title = (TextView)findViewById(R.id.title_viewactivity);
        book_cover = (ImageView)findViewById(R.id.profileimage);
        book_story = (TextView)findViewById(R.id.detail);

        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.avater).error(R.drawable.common_google_signin_btn_icon_dark);

        String image_url = getIntent().getExtras().getString("book_cover");
        // set image using Glide
        Glide.with(View_Reading_Detaile.this).load(image_url).apply(requestOptions).into(book_cover);
        //String image_url = getIntent().getExtras().getString("anime_img") ;
        book_title.setText(getIntent().getStringExtra("book_title"));
        book_story.setText(getIntent().getStringExtra("book_story"));


    }
}
