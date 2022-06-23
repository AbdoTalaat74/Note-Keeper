package com.example.notekeeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.MyHolder>{
    private Context mContext;
    private LayoutInflater inflater;
    private List<CourseInfo> mCourse;

    public CourseRecyclerAdapter(Context mContext, List<CourseInfo> mCourse) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
        this.mCourse = mCourse;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_course_list,parent,false);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        CourseInfo courseInfo = mCourse.get(position);
        holder.courseText.setText(courseInfo.getTitle());

    }

    @Override
    public int getItemCount() {
        return mCourse.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView courseText;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            courseText =(TextView) itemView.findViewById(R.id.text_course);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(mContext,mCourse.get(MyHolder.this.getAdapterPosition()).getTitle()
                    ,Toast.LENGTH_LONG).show();
                }
            });
        }
    }


}
