package com.example.lemon.notetest.bean;

/**
 * Created by Lemon on 2017/5/12.
 */

public class NoteInfo {
    public String content;
    public String date;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NoteInfo{" +
                "content='" + content + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
