package com.example.ethiopis_kids;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.dmoral.toasty.Toasty;

public class Edit_Profile extends AppCompatActivity {
ImageView profile ;
EditText username, age, grade,school;
TextView usertext;
Button update;
private Uri filePath;
    ProgressDialog progressDialog;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
private StorageReference storageReference;
private final int PICK_IMAGE_REQUEST = 71;
FloatingActionButton photo_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ethiopis");

        getSupportActionBar().setElevation(0);

        profile = findViewById(R.id.profile_Page_Image);
        photo_btn = findViewById(R.id.camera_update);
        username=findViewById(R.id.profile_username);
        usertext=findViewById(R.id.textView);
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(this);
        age=findViewById(R.id.profile_Age_hint);
        grade=findViewById(R.id.textView5);
        school=findViewById(R.id.textView3);
        update=findViewById(R.id.update_btn);
        Glide.with(this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile);

        ReadSingleContact();


     photo_btn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
         chooseImage();
         }
     });
update.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if (username.getText().toString().trim().length()==0||grade.getText().toString().trim().length()==0||age.getText().toString().trim().length()==0||school.getText().toString().trim().length()==0)
        {
            Toasty.error(getBaseContext(), getString(R.string.edit_profile_error_message_toast).toString(), Toast.LENGTH_SHORT, true).show();
        }
        else {
            uploadImage();}



    }
});
    }
    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profile.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void upload_profil_image(String userid) {
        Map<String, Object> city = new HashMap<>();
        city.put("Profile_Image", userid);
        city.put("username", username.getText().toString());
        city.put("age", age.getText().toString());
        city.put("grade", grade.getText().toString());
        city.put("school", school.getText().toString());



        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .set(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Toast.makeText(Edit_Profile.this, "DocumentSnapshot successfully written!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                         Intent intent = new Intent(Edit_Profile.this,MainActivity.class);
                         startActivity(intent);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_Profile.this, "Error writing document", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void ReadSingleContact() {
        DocumentReference user = db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task< DocumentSnapshot > task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    username.setText( doc.get("username").toString());
                    usertext.setText(doc.get("username").toString());
                    age.setText(doc.get("age").toString());
                    grade.setText(doc.get("grade").toString());
                    school.setText( doc.get("school").toString());
                    if(doc.get("Profile_Image")!=null){
                        Glide.with(Edit_Profile.this).load(doc.get("Profile_Image").toString()).apply(RequestOptions.circleCropTransform()).into(profile);

                    }
                    else if(doc.get("Profile_Image")=="")
                    {
                        Glide.with(Edit_Profile.this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile);

                    }

                    else
                    {
                        Glide.with(Edit_Profile.this).load(R.drawable.avater).apply(RequestOptions.circleCropTransform()).into(profile);

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
    private void uploadImage() {
        if (filePath != null) {
            //displaying a progress dialog while upload is going on

            Sprite doubleBounce = new DoubleBounce();
            progressDialog.setIndeterminateDrawable(doubleBounce);
            progressDialog.show();

            final StorageReference riversRef = storageReference.child("images/"+FirebaseAuth.getInstance().getCurrentUser().getUid());
            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //if the upload is successfull
                            //hiding the progress dialog

                            riversRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    upload_profil_image(uri.toString());
                                }
                            });
                          //  Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            //if the upload is not successfull
                            //hiding the progress dialog
                            progressDialog.dismiss();

                            //and displaying error message
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //calculating progress percentage
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

                            //displaying percentage in progress dialog
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        }
        //if there is not any file
        else {
            //you can display an error toast
        }
    }}

