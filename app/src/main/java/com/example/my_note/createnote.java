package com.example.my_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createnote extends AppCompatActivity {
EditText createtitleofnote,createcontentofnote;
FloatingActionButton savenote;
ImageButton backbtn1;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
FirebaseFirestore firebaseFirestore;
ProgressBar progressbarofcreatenote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);

        createtitleofnote=findViewById(R.id.createtitleofnote);
        createcontentofnote=findViewById(R.id.createcontentofnote);
        savenote=findViewById(R.id.savenote);
        Toolbar toolbar=findViewById(R.id.toolbarofcreatenote);
        progressbarofcreatenote=findViewById(R.id.progressbarofcreatenote);
        backbtn1=findViewById(R.id.backbtn1);

        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(createnote.this,noteactivity.class));
            }
        });


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


        savenote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title= createtitleofnote.getText().toString();
                String content=createcontentofnote.getText().toString();
                if(title.isEmpty()|| content.isEmpty()){
                    createtitleofnote.setError("Write something");
                    createcontentofnote.setError("fill something");
                }
                else {
                    progressbarofcreatenote.setVisibility(View.VISIBLE);
                    DocumentReference documentReference=firebaseFirestore.
                            collection("Note").document(firebaseUser.getUid()).collection("MyNotes").document();
                    Map<String,Object>note=new HashMap<>();
                    note.put("title",title);
                    note.put("content",content);

                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(createnote.this, "Note Created Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(createnote.this,noteactivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(createnote.this, "Failed to Create", Toast.LENGTH_SHORT).show();
                            progressbarofcreatenote.setVisibility(View.INVISIBLE);

                        }
                    });



                }
            }
        });
    }

  /*  @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    } */
}