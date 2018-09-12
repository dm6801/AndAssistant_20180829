package com.example.admin.andassistant.noteslist;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.admin.andassistant.R;
import com.example.admin.andassistant.databinding.FragmentNotesBinding;
import com.example.admin.andassistant.entities.Note;
import com.example.admin.andassistant.home.HomeActivity;
import com.example.admin.andassistant.infrastructure.BaseApplication;
import com.example.admin.andassistant.infrastructure.BaseFragment;
import com.example.admin.andassistant.infrastructure.noteapi.NoteApi;
import com.example.admin.andassistant.widgets.showcase.ShowcaseDialog;

import javax.inject.Inject;

import androidx.navigation.Navigation;

public class NotesFragment extends BaseFragment {
    public static final String TAG = "NotesFragment";

    public static final String KEY_NOTE_ID = "note_id";

    @Inject
    NoteApi repository;

    FragmentNotesBinding binding;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_notes;
    }

    @Override
    protected ShowcaseDialog getShowcaseInstance() {
        return null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentNotesBinding.bind(view);
        inject();

        initRecyclerView();
        initFloatingActionButton();
    }

    @SuppressWarnings("ConstantConditions")
    private void inject() {
        ((BaseApplication) getContext().getApplicationContext()).getComponent().inject(this);
    }

    private void initRecyclerView() {
        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        NotesAdapter notesAdapter = new NotesAdapter(repository.getAll());
        binding.notesRecyclerView.setAdapter(notesAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int noteId = ((NotesAdapter.NoteViewHolder) viewHolder).noteId;
                repository.deleteItem(repository.findItemById(noteId));
                notesAdapter.notifyDataSetChanged();
            }
        });
        itemTouchHelper.attachToRecyclerView(binding.notesRecyclerView);
    }

    private void initFloatingActionButton() {
        binding.notesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editNote(repository.newItem(), requireActivity());
            }
        });
    }

    public static void editNote(Note note, Context context) {
        Bundle args = new Bundle();
        args.putInt(KEY_NOTE_ID, note.getId());
        Navigation.findNavController((Activity) context, HomeActivity.NAV_HOST_FRAGMENT).navigate(R.id.action_notesFragment_to_editNoteFragment, args);
    }

    @Override
    public void onResume() {
        super.onResume();

        NotesAdapter adapter = (NotesAdapter) binding.notesRecyclerView.getAdapter();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
