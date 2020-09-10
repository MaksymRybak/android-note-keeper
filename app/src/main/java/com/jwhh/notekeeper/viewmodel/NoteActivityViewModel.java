package com.jwhh.notekeeper.viewmodel;

import androidx.lifecycle.ViewModel;

public class NoteActivityViewModel extends ViewModel {
    private String originalNoteCourseId;
    private String originalNoteTitle;
    private String originalNoteText;

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
}
