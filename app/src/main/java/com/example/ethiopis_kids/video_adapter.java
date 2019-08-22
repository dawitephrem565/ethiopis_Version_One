package com.example.ethiopis_kids;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class video_adapter extends RecyclerView.Adapter<video_adapter.ViewHolder> {
    Context context;

    List<Video_items> video_items;
    public video_adapter(){

    }

    public video_adapter( Context cnx, List<Video_items> contents)
    {
        this.context = cnx;
        this.video_items=contents;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.video_custom_view,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      final   Video_items content = video_items.get(position);
     holder.webView.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+content.getVideo_link()+"\" frameborder=\"0\" allowfullscreen></iframe>","text/html","utf-8");
     holder.video_title.setText(content.getVideo_title());
     holder.play.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent = new Intent(context,Video_Play_Screen.class);
             intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
             intent.putExtra("play",content.getVideo_link());
              context.startActivity(intent);
         }
     });

    }

    @Override
    public int getItemCount() {
        return video_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WebView webView;
        TextView video_title;
        Button play;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = (WebView)itemView.findViewById(R.id.webView);
            video_title=itemView.findViewById(R.id.video_title);
            play=itemView.findViewById(R.id.video_play_btn);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient(){

            });
        }

    }
}
