package com.example.my_note;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Khushi_pic extends AppCompatActivity {
ImageButton backbtn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khushi_pic);
        backbtn4=findViewById(R.id.backbtn4);
        backbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Khushi_pic.this,noteactivity.class));
                finish();
            }
        });
    }
}