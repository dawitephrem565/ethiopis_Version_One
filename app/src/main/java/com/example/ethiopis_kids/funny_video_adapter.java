package com.example.ethiopis_kids;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import java.util.List;

public class funny_video_adapter extends RecyclerView.Adapter<funny_video_adapter.ViewHolder> {
    Context context;

    List<funny_video_items> video_items;
    public funny_video_adapter(){

    }

    public funny_video_adapter( Context cnx, List<funny_video_items> contents)
    {
        this.context = cnx;
        this.video_items=contents;
    }


    @NonNull
    @Override
    public funny_video_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.music_video_custom,null);
        return new funny_video_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull funny_video_adapter.ViewHolder holder, int position) {
        funny_video_items content = video_items.get(position);
        holder.webView.loadData("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+content.getVideo_link()+"\" frameborder=\"0\" allowfullscreen></iframe>","text/html","utf-8");


    }

    @Override
    public int getItemCount() {
        return video_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        WebView webView;
        TextView video_link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = (WebView)itemView.findViewById(R.id.webView);

            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient(){

            });
        }
    }
}
