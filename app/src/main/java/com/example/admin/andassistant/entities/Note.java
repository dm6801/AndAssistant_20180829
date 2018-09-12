package com.example.admin.andassistant.entities;

import java.util.Date;

public class Note {
    private int id;
    private Date date;
    private String title;
    private String text;

    public Note() {

    }

    public Note(int id, Date date, String title, String text) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.text = text;
    }

    public Note(Date date, String title) {
        this.date = date;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
