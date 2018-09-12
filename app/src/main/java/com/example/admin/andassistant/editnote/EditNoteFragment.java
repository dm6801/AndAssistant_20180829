package com.example.admin.andassistant.editnote;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.databinding.FragmentEditNoteBinding;
import com.example.admin.andassistant.entities.Note;
import com.example.admin.andassistant.infrastructure.BaseApplication;
import com.example.admin.andassistant.infrastructure.BaseFragment;
import com.example.admin.andassistant.infrastructure.noteapi.NoteApi;
import com.example.admin.andassistant.noteslist.NotesFragment;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;

import java.util.Date;

import javax.inject.Inject;

public class EditNoteFragment extends BaseFragment {
    public static final String TAG = "EditNoteFragment";

    @Inject
    NoteApi repository;

    FragmentEditNoteBinding binding;
    Note note;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_edit_note;
    }

    @Override
    protected ShowcaseDialog getShowcaseInstance() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentEditNoteBinding.bind(view);
        inject();

        setNoteFromArguments();
    }

    @SuppressWarnings("ConstantConditions")
    private void inject() {
        ((BaseApplication) getContext().getApplicationContext()).getComponent().inject(this);
    }

    private void setNoteFromArguments() {
        Bundle args = getArguments();

        if (args != null) {
            int noteId = getArguments().getInt(NotesFragment.KEY_NOTE_ID, -1);
            note = repository.findItemById(noteId);

            binding.noteTitle.setText(note.getTitle());
            binding.noteText.setText(note.getText());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveNote();
    }

    private void saveNote() {
        String noteTitle = binding.noteTitle.getText().toString();
        String noteText = binding.noteText.getText().toString();

        if (noteTitle.trim().isEmpty() || noteText.trim().isEmpty()) {
            return;
        }

        note.setTitle(noteTitle);
        note.setDate(new Date());
        note.setText(noteText);
        repository.updateItem(note);
    }
}
