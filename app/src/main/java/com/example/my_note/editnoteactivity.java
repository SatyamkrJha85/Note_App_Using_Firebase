package com.example.my_note;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class editnoteactivity extends AppCompatActivity {
    Intent data;
    EditText edittitleofnote,editcontentofnote;
    FloatingActionButton saveeditnote;
    ImageButton backbtn3,reaction;

    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnoteactivity);
        edittitleofnote=findViewById(R.id.edittitleofnote);
        editcontentofnote=findViewById(R.id.editcontentofnote);
        saveeditnote=findViewById(R.id.saveeditnote);
        backbtn3=findViewById(R.id.backbtn3);
        reaction=findViewById(R.id.reaction);
        data=getIntent();

        firebaseFirestore=FirebaseFirestore.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();


        reaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(editnoteactivity.this,Khushi_pic.class));
                finish();
            }
        });

        backbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(editnoteactivity.this,notedetails.class));
            }
        });


        saveeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String newtitle = edittitleofnote.getText().toString();
               String newcontent= editcontentofnote.getText().toString();

               if(newtitle.isEmpty()|| newcontent.isEmpty()){
                   Toast.makeText(editnoteactivity.this, "Something is Empty", Toast.LENGTH_SHORT).show();
               }
               else{
                   DocumentReference documentReference=firebaseFirestore.
                           collection("Note").document(firebaseUser.
                                   getUid()).collection("MyNotes").
                           document(data.getStringExtra("noteId"));
                   Map<String,Object>note = new HashMap<>();
                   note.put("title",newtitle);
                   note.put("content",newcontent);
                   documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                       @Override
                       public void onSuccess(Void unused) {
                           Toast.makeText(editnoteactivity.this, "Note is Updated", Toast.LENGTH_SHORT).show();

                           startActivity(new Intent(editnoteactivity.this,noteactivity.class));
                       }
                   }).addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                           Toast.makeText(editnoteactivity.this, "Note not Updated", Toast.LENGTH_SHORT).show();
                       }
                   });
               }
            }
        });

        String notetitle = data.getStringExtra("title");
        String notecontent = data.getStringExtra("content");
        edittitleofnote.setText(notetitle);
        editcontentofnote.setText(notecontent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}