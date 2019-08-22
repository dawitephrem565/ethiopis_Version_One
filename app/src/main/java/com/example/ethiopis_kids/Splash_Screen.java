package com.example.ethiopis_kids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static java.lang.Thread.sleep;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);
        final Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                          try{
                              sleep(1000);

                                  // User is signed in
                                  startActivity(new Intent(Splash_Screen.this,Login_Activity.class));

                          }
                          catch (Exception ex)
                          {
                              Toast.makeText(Splash_Screen.this, ex.toString(), Toast.LENGTH_SHORT).show();
                          }
                    }
                }

        );
        thread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser()!= null) {
            // User is signed in
            startActivity(new Intent(Splash_Screen.this,MainActivity.class));

        }
    }
}
