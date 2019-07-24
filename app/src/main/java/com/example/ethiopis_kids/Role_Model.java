package com.example.ethiopis_kids;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class Role_Model extends AppCompatActivity {
    private RecyclerView list_container;
    // private  DatabaseReference mref;
    //DatabaseReference database;
    ProgressDialog progressDialog;
    Context ctx;
    StorageReference mStorageRef;
    FirebaseFirestore firestore;
    public Reading_content read;
    Rolemodel_Adapter reading_adapter;
    List<Rolemodel_items> list;
    ImageView advert_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role__model);

        // database = FirebaseDatabase.getInstance().getReference("ethiopis/book");

        list = new ArrayList<>();
        reading_adapter = new Rolemodel_Adapter(getBaseContext(),list);
        list_container = (RecyclerView) findViewById(R.id.rolemodel_recycleviwe);
        advert_image = (ImageView)findViewById(R.id.role_advert_logo);
        list_container.setHasFixedSize(true);
        list_container.setLayoutManager(new LinearLayoutManager(this));
        list_container.setAdapter(reading_adapter);
getadvert();
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        firestore = FirebaseFirestore.getInstance();

        firestore.collection("model").orderBy("timestamp", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        Rolemodel_items read= doc.getDocument().toObject(Rolemodel_items.class);
                        list.add(read);
                        reading_adapter.notifyDataSetChanged();
                    }
                }
            }
        });



    }
    public void getadvert(){
        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        firestore = FirebaseFirestore.getInstance();

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

                        Glide.with(getBaseContext()).load(advert_cover.toString()).into(advert_image);

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
