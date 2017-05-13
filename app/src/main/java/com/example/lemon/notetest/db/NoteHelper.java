package com.example.lemon.notetest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lemon on 2017/5/12.
 * 创建数据库
 */

public class NoteHelper extends SQLiteOpenHelper{
    public NoteHelper(Context context) {
        super(context, "NotesDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists notes(id integer primary key autoincrement, " +
                "content text, " +
                "date varchar(20))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
