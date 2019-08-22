package com.example.ethiopis_kids;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import io.fabric.sdk.android.Fabric;

public class Funny_Video_Activity extends AppCompatActivity {
    RecyclerView video_recycleView;
    Vector<Video_items> youtubevideo = new Vector<>();
    List<Video_items> videolist;
    ProgressDialog progressDialog;
    video_adapter video_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funny__video_);
        Fabric.with(this, new Crashlytics());
        video_recycleView  = (RecyclerView)findViewById(R.id.funny_Video_Recycle_View);
        videolist = new ArrayList<>();
        video_adapter = new video_adapter(getBaseContext(),videolist);
        //video_recycleView = (RecyclerView) findViewById(R.id.videoRecycle);

        video_recycleView.setHasFixedSize(true);
        video_recycleView.setLayoutManager(new LinearLayoutManager(this));
        video_recycleView.setAdapter(video_adapter);

        // video_recycleView.setAdapter(video_adapter);
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Video Time Opening Now");
        progressDialog.show();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("video").document("funny").collection("video").orderBy("timestamp").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if(e!=null)
                {
                    Log.d("firestoremessage",e.getMessage());
                    Toast.makeText(Funny_Video_Activity.this, "Unexpected Error", Toast.LENGTH_SHORT).show();
                }
                for (DocumentChange doc: queryDocumentSnapshots.getDocumentChanges() )
                {
                    if(doc.getType()==DocumentChange.Type.ADDED)
                    {
                        Video_items read= doc.getDocument().toObject(Video_items.class);
                        videolist.add(read);
                        progressDialog.dismiss();
                        video_adapter.notifyDataSetChanged();                    }
                }
            }
        });
    }
}
