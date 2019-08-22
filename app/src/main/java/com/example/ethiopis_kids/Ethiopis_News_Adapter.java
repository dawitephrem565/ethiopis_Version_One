package com.example.ethiopis_kids;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Ethiopis_News_Adapter extends RecyclerView.Adapter<Ethiopis_News_Adapter.ViewHolder> {
    Context mcx;
    List<ethiopis_news_data> News_reading_contents;

    public Ethiopis_News_Adapter( Context cnx, List<ethiopis_news_data> contents)
    {
        this.mcx = cnx;
        this.News_reading_contents=contents;
    }
    @NonNull
    @Override
    public Ethiopis_News_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.ethiopis_news_desing_list,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final   ethiopis_news_data content = News_reading_contents.get(position);
        holder.title.setText(content.getHeadline());
        holder.Desc.setText(content.getDescription());
        // Picasso.with(context).load(content.getBook_cover()).into(holder.profileimg);
        Glide.with(mcx).load(content.getImage()).into(holder.profileimg);
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(mcx,R.anim.fad_anim_transation));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("news_title",content.getHeadline());
                intent.putExtra("news_desc",content.getDescription());
                intent.putExtra("news_images",content.getImage());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(mcx,News_View.class);
                mcx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return News_reading_contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        TextView Desc ;
        Button read;
        ImageView profileimg;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.ethiopis_news_title);
            Desc = (TextView)itemView.findViewById(R.id.ehiopis_news_desc);
            profileimg= (ImageView)itemView.findViewById(R.id.news_ethiopis_image);
            cardView = (CardView)itemView.findViewById(R.id.news_card_bord);

        }
    }
}
