package com.sahebul.personalnotebook;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sahebul.personalnotebook.Models.Notes;

import java.util.List;

/**
 * Created by Sahebul on 8/5/19.
 */
public class NotesAdapters extends BaseAdapter {
    MainActivity mainActivity;
    private LayoutInflater inflater;
    List<Notes> data;

    public NotesAdapters(MainActivity mainActivity, List<Notes> data) {
        this.mainActivity = mainActivity;
        inflater = mainActivity.getLayoutInflater();
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Notes notes = data.get(position);
        final NotesAdapters.MyViewHolder mViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.single_notes_layout, parent, false);
            mViewHolder = new NotesAdapters.MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (NotesAdapters.MyViewHolder) convertView.getTag();
        }
        mViewHolder.tv_notes.setText(notes.getNotes());
        mViewHolder.tv_notes_title.setText(notes.getNotesTitle());
        if(!TextUtils.isEmpty(notes.getModifiedAt()))
        mViewHolder.tv_date.setText("Last update: "+notes.getModifiedAt());
        mViewHolder.ll_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mainActivity,AddNotesActivity.class);
                intent.putExtra("title",notes.getNotesTitle());
                intent.putExtra("notes",notes.getNotes());
                intent.putExtra("noteid",notes.getNotesId().toString());
                intent.putExtra("createdAt",notes.getCreatedAt());
                intent.putExtra("lastUpdate",notes.getModifiedAt());
                mainActivity.startActivity(intent);
            }
        });
        return convertView;
    }

    private class MyViewHolder {
        private TextView tv_notes, tv_notes_title,tv_date;
        private LinearLayout ll_container;
        public MyViewHolder(View item) {
            tv_notes = item.findViewById(R.id.tv_notes);
            tv_notes_title = item.findViewById(R.id.tv_notes_title);
            ll_container = item.findViewById(R.id.ll_container);
            tv_date=item.findViewById(R.id.tv_date);

        }
    }
}
