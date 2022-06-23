package com.example.notekeeper.ui.Notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notekeeper.DataManager;
import com.example.notekeeper.NoteInfo;
import com.example.notekeeper.R;
import com.example.notekeeper.noteRecyclerAdapter;

import java.util.List;

public class NotesFragment extends Fragment {
    private noteRecyclerAdapter adapter;
    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        root = inflater.inflate(R.layout.fragment_notes,container,false);
        final RecyclerView recyclerView = root.findViewById(R.id.list_items);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(layoutManager);
        List<NoteInfo> notes = DataManager.getInstance().getNotes();
        adapter = new noteRecyclerAdapter(container.getContext(), notes);
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
//        notesAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
    }
}