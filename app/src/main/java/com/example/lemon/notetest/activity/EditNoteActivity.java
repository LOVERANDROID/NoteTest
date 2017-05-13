package com.example.lemon.notetest.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.lemon.notetest.R;
import com.example.lemon.notetest.adapter.NoteAdapter;
import com.example.lemon.notetest.bean.NoteInfo;
import com.example.lemon.notetest.db.NoteDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class EditNoteActivity extends AppCompatActivity {
    private EditText editText;
    private NoteDao mDao = new NoteDao(this);
    private List<NoteInfo> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //自动弹出输入法键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        setContentView(R.layout.activity_edit_note);
        Init();
        Intent intent = getIntent();
        String content_str = intent.getStringExtra("content");
        editText.setText(content_str);

    }
    private void Init(){
        editText = (EditText) findViewById(R.id.editnote_edittext);
    }
    private void add(){
        String content = editText.getText().toString().trim();
        if (!content.isEmpty()){
            NoteInfo info = new NoteInfo();
            mDao.insert(content, getDate());
            info.content = content;
            info.date = getDate();
            list.add(info);
        }
    }
    public String getDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年mm月dd日  HH:mm");
        String date = format.format(System.currentTimeMillis());
        return date;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       switch (keyCode){
           case KeyEvent.KEYCODE_BACK:
               add();
               setResult(1);
               finish();
               break;
       }
        return super.onKeyDown(keyCode, event);
    }
}
