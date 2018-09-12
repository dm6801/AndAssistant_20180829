package com.example.admin.andassistant.noteslist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.Utilities;
import com.example.admin.andassistant.databinding.ListItemNoteBinding;
import com.example.admin.andassistant.entities.Note;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {
    public static final int CELL_BACKGROUND_ALPHA = 230;

    private List<Note> notes;
    private Context context;

    public NotesAdapter(List<Note> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ListItemNoteBinding binding = ListItemNoteBinding.inflate(inflater, viewGroup, false);
        return new NoteViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder viewHolder, int i) {
        Note note = notes.get(i);
        viewHolder.bind(note, context);
        zebraStripingBackground(viewHolder, i);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    private void zebraStripingBackground(NoteViewHolder viewHolder, int position) {
        CardView cardView = viewHolder.binding.notesItemCard;
        Context context = viewHolder.binding.notesItemCard.getContext();

        cardView.setCardBackgroundColor(
                Utilities.colorWithAlpha(ContextCompat.getColor(context,
                        position % 2 == 0 ? R.color.colorAccent : R.color.colorPrimary
                ), CELL_BACKGROUND_ALPHA)
        );
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        protected ListItemNoteBinding binding;
        public int noteId;

        public NoteViewHolder(ListItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Note note, Context context) {
            noteId = note.getId();
            binding.notesItemTitle.setText(note.getTitle());
            binding.notesItemCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NotesFragment.editNote(note, context);
                }
            });
            binding.executePendingBindings();
        }
    }
}
