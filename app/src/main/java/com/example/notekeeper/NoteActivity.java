package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    Spinner courseSpinner;
    EditText noteTitle;
    EditText noteBody;
    private boolean mIsNewNote;
    NoteInfo noteInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        noteTitle = findViewById(R.id.tetxt_note_title);
        noteBody = findViewById(R.id.text_note);

        courseSpinner = (Spinner) findViewById(R.id.spinner_courses);


        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> courseAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);

        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        courseSpinner.setAdapter(courseAdapter);

        readDisplayStateValues();
        if(!mIsNewNote)
            displayNote(courseSpinner, noteTitle, noteBody);

    }



    private void displayNote(Spinner spinner, EditText editText, EditText editText2){
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(noteInfo.getCourse());

        courseSpinner.setSelection(courseIndex);
        noteTitle.setText(noteInfo.getTitle());
        noteBody.setText(noteInfo.getText());
    }

    private void readDisplayStateValues(){

        Intent intent = getIntent();
        noteInfo = intent.getParcelableExtra("Note Item");
        mIsNewNote = noteInfo == null;

    }


}