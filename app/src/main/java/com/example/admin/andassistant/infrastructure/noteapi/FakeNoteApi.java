package com.example.admin.andassistant.infrastructure.noteapi;

import com.example.admin.andassistant.entities.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeNoteApi implements NoteApi {
    public static final String NOTE_A_TITLE = "Note a/1";
    public static final String NOTE_A_TEXT = "This is a test note";

    public static final String NOTE_B_TITLE = "Note b/2";
    public static final String NOTE_B_TEXT = "Note 2 - with a new line \r\nNew Line <----";

    public static final String NOTE_C_TITLE = "Note c/3";
    public static final String NOTE_C_TEXT = "Lorem ipsum dolor sit amet," +
            " consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore" +
            " et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco" +
            " laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit" +
            " in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat" +
            " cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    public static final int NEW_NOTE_ID = -1;

    public static final String EMPTY_FIELD = "";

    private final List<Note> notes;

    public FakeNoteApi() {
        notes = new ArrayList<>();
        notes.add(new Note(1, currentDate(), NOTE_A_TITLE, NOTE_A_TEXT));
        notes.add(new Note(2, currentDate(), NOTE_B_TITLE, NOTE_B_TEXT));
        notes.add(new Note(3, currentDate(), NOTE_C_TITLE, NOTE_C_TEXT));
    }

    @Override
    public Note newItem() {
        return new Note(NEW_NOTE_ID, currentDate(), EMPTY_FIELD, EMPTY_FIELD);
    }

    private Date currentDate() {
        return new Date();
    }

    @Override
    public List<Note> getAll() {
        return notes;
    }

    @Override
    public Note findItemById(int id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }

        return newItem();
    }

    @Override
    public void updateItem(Note note) {
        if (!isNewNote(note)) {
            return;
        }

        int lastId = -1;

        if (notes.size() > 0) {
            lastId = notes.get(notes.size() - 1).getId();
        }

        note.setId(lastId + 1);
        notes.add(note);
    }

    @Override
    public void deleteItem(Note note) {
        notes.remove(note);
    }

    private boolean isNewNote(Note note) {
        return note.getId() == NEW_NOTE_ID;
    }

}
