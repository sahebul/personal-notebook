package com.sahebul.personalnotebook.Models;

import java.sql.Timestamp;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sahebul on 8/5/19.
 */
public class Notes extends RealmObject {
    @PrimaryKey
    private Integer notesId;
    private String notesTitle;
    private String notes;
    private String createdAt;
    private String modifiedAt;

    public Notes() {
    }

    public Notes(Integer notesId, String notesTitle, String notes, String createdAt, String modifiedAt) {
        this.notesId = notesId;
        this.notesTitle = notesTitle;
        this.notes = notes;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Integer getNotesId() {
        return notesId;
    }

    public void setNotesId(Integer notesId) {
        this.notesId = notesId;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(String modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
