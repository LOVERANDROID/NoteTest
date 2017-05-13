package com.example.lemon.notetest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lemon.notetest.R;
import com.example.lemon.notetest.bean.NoteInfo;

import java.util.List;

/**
 * Created by Lemon on 2017/5/12.
 */

public class NoteAdapter extends BaseAdapter{
    private List<NoteInfo> list;
    private Context context;
    public NoteAdapter(Context context, List<NoteInfo> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder viewHolder = null;
        if (convertView != null){
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.note_list_item, null);
            viewHolder = new ViewHolder();
             viewHolder.content = (TextView) view.findViewById(R.id.content_textview);
            viewHolder.date = (TextView) view.findViewById(R.id.date_textview);
            view.setTag(viewHolder);
        }
        //String content_string = list.get(position).getContent();

            viewHolder.content.setText(list.get(position).getContent());
            viewHolder.date.setText(list.get(position).getDate());
            return view;

    }

     static class ViewHolder{
        TextView content;
        TextView date;
    }
}
