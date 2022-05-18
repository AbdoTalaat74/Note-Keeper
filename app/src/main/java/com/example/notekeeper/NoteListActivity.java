package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.navigation.ui.AppBarConfiguration;

import com.example.notekeeper.databinding.ActivityNoteListActivtyBinding;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    public void onClick(View view){
        Intent fabIntent = new Intent(NoteListActivity.this, NoteActivity.class);
        startActivity(fabIntent);
    }

    private AppBarConfiguration appBarConfiguration;
    private ActivityNoteListActivtyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNoteListActivtyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        initializeDisplayContent();

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent fabIntent = new Intent(NoteListActivity.this, NoteActivity.class);
//                startActivity(fabIntent);
//            }
//        });


    }



    private void initializeDisplayContent(){

        ListView listNotes = findViewById(R.id.course_list);

        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        ArrayAdapter<NoteInfo> notesAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,notes);
        listNotes.setAdapter(notesAdapter);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getBaseContext(), NoteActivity.class);
                intent.putExtra("Note Item",notes.get(position));
                startActivity(intent);

            }
        });
    }


}