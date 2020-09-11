package com.jwhh.notekeeper;

import com.jwhh.notekeeper.model.CourseInfo;
import com.jwhh.notekeeper.model.DataManager;
import com.jwhh.notekeeper.model.NoteInfo;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataManagerTest {

    @Test
    public void createNewNote() {
        DataManager dataManager = DataManager.getInstance();
        final CourseInfo courseInfo = dataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText= "Test note text";

        int newNoteIdx = dataManager.createNewNote();
        NoteInfo newNote = dataManager.getNotes().get(newNoteIdx);
        newNote.setCourse(courseInfo);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote = dataManager.getNotes().get(newNoteIdx);
        assertEquals(compareNote.getCourse(), courseInfo);
        assertEquals(compareNote.getTitle(), noteTitle);
        assertEquals(compareNote.getText(), noteText);
    }
}