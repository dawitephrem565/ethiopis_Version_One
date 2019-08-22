package com.example.ethiopis_kids;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.iid.FirebaseInstanceId;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;


import io.fabric.sdk.android.Fabric;

public class Reading_Time_Activity  extends Activity {
  private   RecyclerView list_container;
  // private  DatabaseReference mref;
   //DatabaseReference database;
   ProgressDialog progressDialog;
   Context ctx;
   FloatingActionButton play;
    private FirebaseAnalytics mFirebaseAnalytics;
   ImageView reading_advert_banner;
   FirebaseFirestore firestore;
  public Reading_content read;
   Reading_Adapter reading_adapter;
List<Reading_content> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_recycle_view);
        // database = FirebaseDatabase.getInstance().getReference("ethiopis/book");

         firestore = FirebaseFirestore.getInstance();
        Fabric.with(this, new Crashlytics());
        // Glide.with(this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile_page);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        list = new ArrayList<>();
        reading_adapter = new Reading_Adapter(getBaseContext(),list);
        list_container = (RecyclerView) findViewById(R.id.Reading_Recycle_View);

        reading_advert_banner = (ImageView)findViewById(R.id.read_advert_logo);
        list_container.setHasFixedSize(true);
        list_container.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        list_container.setAdapter(reading_adapter);

getadvert();

     firestore.collection("book").orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
         @Override
         public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
             if(e!=null)
             {
                 Log.d("firestoremessage",e.getMessage());
             }
            for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges() )
            {
                if(doc.getType()==DocumentChange.Type.ADDED)
                {
              Reading_content read= doc.getDocument().toObject(Reading_content.class);
              list.add(read);
              reading_adapter.notifyDataSetChanged();
                }
            }
         }
     });



    }
    public void getadvert(){


        firestore.collection("book_advert").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if(e!=null)
                {
                    Log.d("advert",e.getMessage());
                }
                for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges() )
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {

                        String advert_cover =  doc.getDocument().getString("advert_cover");

                        Glide.with(getBaseContext()).load(advert_cover.toString()).into(reading_advert_banner);

                    }
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();

    }
}

