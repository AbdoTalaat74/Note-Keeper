package com.example.notekeeper.ui.Courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notekeeper.CourseInfo;
import com.example.notekeeper.CourseRecyclerAdapter;
import com.example.notekeeper.DataManager;
import com.example.notekeeper.R;

import java.util.List;


public class CoursesFragment extends Fragment {
    View root;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_courses,container,false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_courses);
        GridLayoutManager layoutManager = new GridLayoutManager(container.getContext(),2);
        recyclerView.setLayoutManager(layoutManager);

        List<CourseInfo> courseInfoList = DataManager.getInstance().getCourses();
        CourseRecyclerAdapter adapter = new CourseRecyclerAdapter(getContext(),courseInfoList);
        recyclerView.setAdapter(adapter);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}