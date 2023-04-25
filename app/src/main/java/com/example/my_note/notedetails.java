package com.example.my_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class notedetails extends AppCompatActivity {
    private TextView titleofnotedetail, contentofnotedetail;
    FloatingActionButton gotoeditnote;
    ImageButton backbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);
        titleofnotedetail = findViewById(R.id.titleofnotedetail);
        contentofnotedetail = findViewById(R.id.contentofnotedetail);
        gotoeditnote = findViewById(R.id.gotoeditnote);
        backbtn = findViewById(R.id.backbtn);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(notedetails.this, noteactivity.class));
            }
        });

        Intent data = getIntent();
        gotoeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), editnoteactivity.class);
                intent.putExtra("title", data.getStringExtra("title"));
                intent.putExtra("content", data.getStringExtra("content"));
                intent.putExtra("noteId", data.getStringExtra("noteId"));
                v.getContext().startActivity(intent);
            }
        });

        contentofnotedetail.setText(data.getStringExtra("content"));
        titleofnotedetail.setText(data.getStringExtra("title"));

    }
}