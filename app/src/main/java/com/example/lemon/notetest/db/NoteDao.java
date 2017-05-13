package com.example.lemon.notetest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lemon.notetest.bean.NoteInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lemon on 2017/5/12.
 */

public class NoteDao {
    private NoteHelper mNoteHelper;
    public NoteDao(Context context){
        mNoteHelper = new NoteHelper(context);
    }
    //插入数据
    public void insert(String content, String date){
        SQLiteDatabase db = mNoteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", content);
        values.put("date", date);
        db.insert("notes", null, values);
        db.close();
    }
    //修改数据
    public void update(String content, String date){
        SQLiteDatabase db = mNoteHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("content", content);
        values.put("date", date);
        db.update("notes", values, "content = ? and date = ？", new String []{content, date});
        db.close();
    }
    //删除数据
    public void delete(String content){
        SQLiteDatabase db = mNoteHelper.getWritableDatabase();
        db.delete("notes", "content = ?", new String []{content});
        db.close();
    }
    //查询数据
    public List<NoteInfo> query(){
        SQLiteDatabase db = mNoteHelper.getWritableDatabase();
        List<NoteInfo> list = new ArrayList<>();
        Cursor cursor = db.query("notes", new String[]{"content", "date"}, null, null, null, null, "id desc");
        while (cursor.moveToNext()){
            NoteInfo info = new NoteInfo();
            //info.setContent(cursor.getString(0));
            //info.setDate(cursor.getString(1));
            info.content = cursor.getString(0);
            info.date = cursor.getString(1);
            cursor.getString(1);
            list.add(info);
        }
        cursor.close();
        db.close();
        return list;
    }
}
