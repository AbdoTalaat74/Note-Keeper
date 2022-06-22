package com.example.notekeeper;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class noteRecyclerAdapter extends RecyclerView.Adapter<noteRecyclerAdapter.noteRecyclerHolder> {

    private final Context mContext;
    private final List<NoteInfo> mNotes;
    private final LayoutInflater layoutInflater;
    NoteInfo noteInfo;
    public noteRecyclerAdapter(Context context, List<NoteInfo> notes) {
        mContext = context;
        layoutInflater = LayoutInflater.from(context);
        this.mNotes = notes;
    }

    @NonNull
    @Override
    public noteRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.item_note_list,parent,false);
        return new noteRecyclerHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull noteRecyclerHolder holder,int position) {
        NoteInfo note = mNotes.get(position);
        holder.courseText.setText(note.getCourse().getTitle());
        holder.titleText.setText(note.getTitle());



    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public class noteRecyclerHolder extends RecyclerView.ViewHolder {

        public final TextView courseText;
        public final TextView titleText;
        int mPosition;

        public noteRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            courseText = (TextView) itemView.findViewById(R.id.text_course);
            titleText = (TextView) itemView.findViewById(R.id.text_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext,NoteActivity.class);
                    intent.putExtra("Note Position",noteRecyclerHolder.this.getAdapterPosition());
                    mContext.startActivity(intent);
                }
            });

        }

    }




}
