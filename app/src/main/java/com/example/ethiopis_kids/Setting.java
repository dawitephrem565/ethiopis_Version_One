package com.example.ethiopis_kids;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Setting extends AppCompatActivity implements View.OnClickListener {
TextView myprofile,languge,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        myprofile=findViewById(R.id.myprofile);
        languge=findViewById(R.id.languge);
        logout=findViewById(R.id.logout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ethiopis");

        myprofile.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.myprofile:
                startActivity(new Intent(Setting.this,Profile_Page.class));
          break;
            case R.id.languge:

                break;
            case R.id.logout:
                startActivity(new Intent(Setting.this,Login_Activity.class));
                finish();
                break;

        }

    }
}
