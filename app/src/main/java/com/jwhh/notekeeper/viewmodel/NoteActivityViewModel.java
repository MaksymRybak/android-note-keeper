package com.jwhh.notekeeper.viewmodel;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class NoteActivityViewModel extends ViewModel {
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.jwhh.notekeeper.viewmodel.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE = "com.jwhh.notekeeper.viewmodel.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT = "com.jwhh.notekeeper.viewmodel.ORIGINAL_NOTE_TEXT";

    private String originalNoteCourseId;
    private String originalNoteTitle;
    private String originalNoteText;
    private Boolean isNewlyCreated;

    public NoteActivityViewModel() {
        isNewlyCreated = true;
    }

    public String getOriginalNoteCourseId() {
        return originalNoteCourseId;
    }

    public void setOriginalNoteCourseId(String originalNoteCourseId) {
        this.originalNoteCourseId = originalNoteCourseId;
    }

    public String getOriginalNoteTitle() {
        return originalNoteTitle;
    }

    public void setOriginalNoteTitle(String originalNoteTitle) {
        this.originalNoteTitle = originalNoteTitle;
    }

    public String getOriginalNoteText() {
        return originalNoteText;
    }

    public void setOriginalNoteText(String originalNoteText) {
        this.originalNoteText = originalNoteText;
    }

    public Boolean getNewlyCreated() {
        return isNewlyCreated;
    }

    public void setNewlyCreated(Boolean newlyCreated) {
        isNewlyCreated = newlyCreated;
    }

    public void saveState(Bundle outState) {
        outState.putString(ORIGINAL_NOTE_COURSE_ID, originalNoteCourseId);
        outState.putString(ORIGINAL_NOTE_TITLE, originalNoteTitle);
        outState.putString(ORIGINAL_NOTE_TEXT, originalNoteText);
    }

    public void restoreState(Bundle inState) {
        originalNoteCourseId = inState.getString(ORIGINAL_NOTE_COURSE_ID);
        originalNoteTitle = inState.getString(ORIGINAL_NOTE_TITLE);
        originalNoteText = inState.getString(ORIGINAL_NOTE_TEXT);
    }
}
