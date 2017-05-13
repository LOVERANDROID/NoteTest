package com.example.lemon.notetest.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.lemon.notetest.R;
import com.example.lemon.notetest.adapter.NoteAdapter;
import com.example.lemon.notetest.bean.NoteInfo;
import com.example.lemon.notetest.db.NoteDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton fab;
    private ListView noteListView;
    private List<NoteInfo> list;
    private NoteAdapter mNoteAdapter;
    private NoteDao mDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        onClick();
        ShowList();
    }
    public void Init(){
        fab = (FloatingActionButton) findViewById(R.id.fab);
        noteListView = (ListView) findViewById(R.id.listview);
    }
    private void onClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, EditNoteActivity.class);
                intent.putExtra("content",list.get(position).content);
                startActivityForResult(intent, 1);
                mDao.delete(list.get(position).content);
            }
        });
        noteListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       mDao.delete(list.get(position).content);
                        list.remove(list.get(position));
                        mNoteAdapter.notifyDataSetChanged();
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("确认删除该条便签");
                builder.setPositiveButton("是", listener);
                builder.setNegativeButton("否", null);
                builder.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                ShowList();
                break;
        }
    }

    /**
     * 将数据库中的数据显示在列表上
     */
    private void ShowList(){
        list = new ArrayList<NoteInfo>();
        mDao = new NoteDao(this);
        list = mDao.query();
        mNoteAdapter = new NoteAdapter(this, list);
        noteListView.setAdapter(mNoteAdapter);
    }
}
