package com.harshshende.checkmate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.harshshende.checkmate.Models.Notes;

import java.util.Date;

public class NotesTakerActivity extends AppCompatActivity {
    //Creating variables
    EditText editTextTitle,editTextContent;
    ImageView imageViewSave;
    Notes note;
    Boolean isOldNoteOrNot=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_taker);
        imageViewSave=findViewById(R.id.imageViewSave);
        editTextTitle=findViewById(R.id.editTextTitle);
        editTextContent=findViewById(R.id.editTextContent);
        note=new Notes();
        try {
            note=(Notes)getIntent().getSerializableExtra("OldNotes");
            editTextTitle.setText(note.getTITLE());
            editTextContent.setText(note.getCONTENT());
            isOldNoteOrNot=true;
        } catch(Exception e) {
            e.printStackTrace();
        }
        imageViewSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=editTextTitle.getText().toString();
                String content=editTextContent.getText().toString();
                if(content.isEmpty()) {
                    if(title.isEmpty()) {
                        Toast.makeText(NotesTakerActivity.this,"Please enter the title and content!",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(NotesTakerActivity.this,"Please enter the content!",Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
                SimpleDateFormat form=new SimpleDateFormat("EEE, dd MMM yyyy HH:mm a");
                Date date=new Date();
                if(!isOldNoteOrNot) {
                    note=new Notes();
                }
                note.setTITLE(title);
                note.setCONTENT(content);
                note.setDATES(form.format(date));
                Intent i01=new Intent();
                i01.putExtra("Note",note);
                setResult(Activity.RESULT_OK,i01);
                Toast.makeText(NotesTakerActivity.this,"Note created!",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}