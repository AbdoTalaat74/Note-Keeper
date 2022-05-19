package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final int POSITION_NOT_SET = -1;
    Spinner courseSpinner;
    EditText noteTitle;
    EditText noteBody;
    private boolean mIsNewNote;
    NoteInfo noteInfo;
    private int notePosition;
    private boolean isCancelling;

    @Override
    protected void onPause() {
        super.onPause();
        if (isCancelling) {
            if (mIsNewNote) {
                DataManager.getInstance().removeNote(notePosition);
            }
        } else {
            saveNote();
        }

    }

    private void saveNote() {
        noteInfo.setCourse((CourseInfo) courseSpinner.getSelectedItem());
        noteInfo.setTitle(noteTitle.getText().toString());
        noteInfo.setText(noteBody.getText().toString());
    }

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
        if (!mIsNewNote)
            displayNote(courseSpinner, noteTitle, noteBody);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_email) {
            sendEmail();
            return true;
        } else if (id == R.id.action_cancel) {
            isCancelling = true;
            finish();
        }
        return super.onOptionsItemSelected(item);


    }

    private void sendEmail() {
        CourseInfo course = (CourseInfo) courseSpinner.getSelectedItem();
        String subject = noteTitle.getText().toString();
        String text = "Checkout what I learned in the Pluralsight course \n" + course.getTitle() +
                "\n" + noteTitle.getText();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);

    }

    private void displayNote(Spinner spinner, EditText editText, EditText editText2) {
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courseIndex = courses.indexOf(noteInfo.getCourse());

        courseSpinner.setSelection(courseIndex);
        noteTitle.setText(noteInfo.getTitle());
        noteBody.setText(noteInfo.getText());
    }

    private void readDisplayStateValues() {

        Intent intent = getIntent();
        int position = intent.getIntExtra("Note Position", POSITION_NOT_SET);
        mIsNewNote = position == POSITION_NOT_SET;
        if (mIsNewNote) {

            createNewNote();

        } else {
            noteInfo = DataManager.getInstance().getNotes().get(position);
        }

    }

    private void createNewNote() {
        DataManager dataManager = DataManager.getInstance();
        notePosition = dataManager.createNewNote();
        noteInfo = dataManager.getNotes().get(notePosition);

    }


}