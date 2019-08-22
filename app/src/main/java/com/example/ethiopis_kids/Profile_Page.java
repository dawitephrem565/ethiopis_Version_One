package com.example.ethiopis_kids;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

public class Profile_Page extends AppCompatActivity {
ImageView ProfileImage;
TextView username ,age , grade, school,userprofile;
Toolbar toolbar;
FloatingActionButton edit_btn;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile__page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setElevation(0);
        ProfileImage = (ImageView)findViewById(R.id.profile_Page_Image);
        userprofile=(TextView)findViewById(R.id.textView) ;
        username = findViewById(R.id.profile_username);
        age=findViewById(R.id.profile_Age_hint);
        grade=findViewById(R.id.textView5);
        school=findViewById(R.id.textView3);
        edit_btn=findViewById(R.id.dailybookorder);
        Glide.with(this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(ProfileImage);
        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile_Page.this,Edit_Profile.class));
            }
        });
ReadSingleContact();
        }

    private void ReadSingleContact() {
        DocumentReference user = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
              username.setText( doc.get("username").toString());
              userprofile.setText( doc.get("username").toString());
               age.setText(doc.get("age").toString());
               grade.setText(doc.get("grade").toString());
                  school.setText( doc.get("school").toString());
                   if(doc.get("Profile_Image")!=null){
                       Glide.with(Profile_Page.this).load(doc.get("Profile_Image").toString()).apply(RequestOptions.circleCropTransform()).into(ProfileImage);

                   }
                   else
                   {
                       Glide.with(Profile_Page.this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(ProfileImage);

                   }
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }

}



