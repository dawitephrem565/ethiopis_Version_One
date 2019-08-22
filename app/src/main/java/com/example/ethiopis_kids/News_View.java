package com.example.ethiopis_kids;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class News_View extends Activity {
    ImageView News_image;
    FloatingActionButton url_btn;
    TextView title ,desc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ethiopis_news_design);
        title = (TextView)findViewById(R.id.News_title);
        News_image = (ImageView)findViewById(R.id.News_Image);
        desc = (TextView)findViewById(R.id.News_Desc);

        RequestOptions requestOptions= new RequestOptions().placeholder(R.drawable.avater).error(R.drawable.common_google_signin_btn_icon_dark);

        String image_url = getIntent().getExtras().getString("news_images");
        // set image using Glide
        Glide.with(News_View.this).load(image_url).apply(requestOptions).into(News_image);
        //String image_url = getIntent().getExtras().getString("anime_img") ;
        title.setText(getIntent().getStringExtra("news_title"));
        desc.setText(getIntent().getStringExtra("news_desc"));


    }
}
