package com.example.admin.andassistant.infrastructure.noteapi;

import com.example.admin.andassistant.entities.Note;

import java.util.List;

public interface NoteApi {
    Note newItem();

    List<Note> getAll();

    Note findItemById(int id);

    void updateItem(Note note);
}
