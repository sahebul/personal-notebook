package com.sahebul.personalnotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.sahebul.personalnotebook.Models.Notes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class AddNotesActivity extends AppCompatActivity {
    private Realm realm;
    EditText et_note_title;
    EditText et_note;
    TextView tv_lastupdate;
    int nextId;
    String createdAt = "", updatedAt = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        et_note_title = (EditText) findViewById(R.id.et_note_title);
        et_note = (EditText) findViewById(R.id.et_note);
        tv_lastupdate = findViewById(R.id.tv_date);
        getActionBar().setTitle("Add Notes");
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        realm = Realm.getInstance(config);
        String previousID = "";
        String previousNotes = "";
        String previousTitle = "";
        String lastupdate = "";
        Intent intent = getIntent();
        if (intent.hasExtra("noteid")) {
            previousID = getIntent().getExtras().getString("noteid");
        }
        if (intent.hasExtra("notes")) {
            previousNotes = getIntent().getExtras().getString("notes");
        }
        if (intent.hasExtra("title")) {
            previousTitle = getIntent().getExtras().getString("title");
        }
        String previouscreatedAt = "";
        if (intent.hasExtra("createdAt")) {
            previouscreatedAt = getIntent().getExtras().getString("createdAt");
        }
        if (intent.hasExtra("lastUpdate")) {
            lastupdate = getIntent().getExtras().getString("lastUpdate");
        }

        if (TextUtils.isEmpty(previousID)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            createdAt = dateFormat.format(date);
            realm.beginTransaction();
            Number currentIdNum = realm.where(Notes.class).max("notesId");

            if (currentIdNum == null) {
                nextId = 1;
            } else {
                nextId = currentIdNum.intValue() + 1;
            }
            realm.commitTransaction();
        } else {
            nextId = Integer.parseInt(previousID);
            et_note_title.setText(previousTitle);
            et_note.setText(previousNotes);
            if (!TextUtils.isEmpty(lastupdate)) {
                tv_lastupdate.setText("Last update: " + lastupdate);
            }
            DateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");
            Date date = new Date();
            createdAt = previouscreatedAt;
            updatedAt = dateFormat.format(date);
        }

        et_note_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    //do the task
                    realm.beginTransaction();
                    Notes notes = new Notes(nextId, et_note_title.getText().toString(), et_note.getText().toString(), createdAt, updatedAt); // unmanaged
                    realm.copyToRealmOrUpdate(notes);
                    realm.commitTransaction();
                }
            }
        });
        et_note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    //do the task
                    realm.beginTransaction();
                    Notes notes = new Notes(nextId, et_note_title.getText().toString(), et_note.getText().toString(), createdAt, updatedAt);
                    realm.copyToRealmOrUpdate(notes);
                    realm.commitTransaction();
                }

            }
        });
    }
}
