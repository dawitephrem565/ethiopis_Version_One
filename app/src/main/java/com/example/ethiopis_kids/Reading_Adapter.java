package com.example.ethiopis_kids;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Reading_Adapter extends RecyclerView.Adapter<Reading_Adapter.ViewHolder> {
    Context mcx;
   List<Reading_content> reading_contents;

    public Reading_Adapter( Context cnx, List<Reading_content> contents)
    {
        this.mcx = cnx;
       this.reading_contents=contents;
    }
    @NonNull
    @Override
    public Reading_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.reading_custom_view,null);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      final   Reading_content content = reading_contents.get(position);
        holder.title.setText(content.getBook_title());
        holder.Desc.setText(content.getBook_story());
      // Picasso.with(context).load(content.getBook_cover()).into(holder.profileimg);
       Glide.with(mcx).load(content.getBook_cover()).into(holder.profileimg);

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.putExtra("book_title",content.getBook_title());
               intent.putExtra("book_story",content.getBook_story());
               intent.putExtra("book_cover",content.getBook_cover());
               intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
               intent.setClass(mcx,View_Reading_Detaile.class);
               mcx.startActivity(intent);
           }
       });


    }

    @Override
    public int getItemCount() {
        return reading_contents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        TextView Desc ;
        Button read;
      ImageView profileimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           title = (TextView)itemView.findViewById(R.id.reading_title);
           Desc = (TextView)itemView.findViewById(R.id.story_desc);
           profileimg= (ImageView)itemView.findViewById(R.id.reading_image);


        }
    }
}
