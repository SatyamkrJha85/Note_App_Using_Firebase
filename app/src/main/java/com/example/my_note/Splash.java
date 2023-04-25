package com.example.my_note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseUser currentuser= FirebaseAuth.getInstance().getCurrentUser();
                if(currentuser==null){
                    startActivity(new Intent(Splash.this,Log_in.class));
                } else {
                    startActivity(new Intent(Splash.this,noteactivity.class));
                }
                finish();

            }
        },1000);
    }
}