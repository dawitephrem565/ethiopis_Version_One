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

import com.bumptech.glide.Glide;

import java.util.List;

public class Rolemodel_Adapter  extends RecyclerView.Adapter<Rolemodel_Adapter.ViewHolder> {
    Context mcx;
    List<Rolemodel_items> rolemodel_items;

    public Rolemodel_Adapter( Context cnx, List<Rolemodel_items> contents)
    {
        this.mcx = cnx;
        this.rolemodel_items=contents;
    }
    @NonNull
    @Override
    public Rolemodel_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater =  LayoutInflater.from(mcx);
        View view = inflater.inflate(R.layout.rolemode_customview,null);
        return new Rolemodel_Adapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Rolemodel_Adapter.ViewHolder holder, int position) {
        final   Rolemodel_items content = rolemodel_items.get(position);
        holder.title.setText(content.getModel_title());
        holder.Desc.setText(content.getModel_desc());
        // Picasso.with(context).load(content.getBook_cover()).into(holder.profileimg);
        Glide.with(mcx).load(content.getModel_cover()).into(holder.profileimg);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("book_title",content.getModel_title());
                intent.putExtra("book_story",content.getModel_desc());
                intent.putExtra("book_cover",content.getModel_cover());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setClass(mcx,View_Reading_Detaile.class);
                mcx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return rolemodel_items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title ;
        TextView Desc ;
        Button read;
        ImageView profileimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.Model_title);
            Desc = (TextView)itemView.findViewById(R.id.Model_desc);
            profileimg= (ImageView)itemView.findViewById(R.id.Model_image);


        }
    }
}