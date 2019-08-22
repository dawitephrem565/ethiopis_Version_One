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

public class Music_Video_activity extends AppCompatActivity {

    RecyclerView video_recycleView;
    Vector<music_video_items> youtubevideo = new Vector<>();
    List<music_video_items> videolist;
    ProgressDialog progressDialog;
    music_video_adapter video_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music__video_activity);
        Fabric.with(this, new Crashlytics());
        video_recycleView = (RecyclerView) findViewById(R.id.Reading_Video_Recycle_View);
        videolist = new ArrayList<>();
        video_adapter = new music_video_adapter(getBaseContext(), videolist);
        //video_recycleView = (RecyclerView) findViewById(R.id.videoRecycle);

        video_recycleView.setHasFixedSize(true);
        video_recycleView.setLayoutManager(new LinearLayoutManager(this));
        video_recycleView.setAdapter(video_adapter);

        // video_recycleView.setAdapter(video_adapter);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Video Time Opening Now");
        progressDialog.show();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();

        firestore.collection("video").document("music").collection("video").orderBy("timestamp").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {
                if (e != null) {
                    Log.d("firestoremessage", e.getMessage());
                    Toast.makeText(Music_Video_activity.this, "Unexpected Error", Toast.LENGTH_SHORT).show();
                }
                for (DocumentChange doc : queryDocumentSnapshots.getDocumentChanges()) {
                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        music_video_items read = doc.getDocument().toObject(music_video_items.class);
                        videolist.add(read);
                        progressDialog.dismiss();
                        video_adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
