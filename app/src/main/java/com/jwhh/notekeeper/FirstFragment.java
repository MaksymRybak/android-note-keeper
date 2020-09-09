package com.jwhh.notekeeper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.jwhh.notekeeper.model.CourseInfo;
import com.jwhh.notekeeper.model.DataManager;
import com.jwhh.notekeeper.model.NoteInfo;

import java.util.List;

public class FirstFragment extends Fragment {

    private NoteInfo note;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        Spinner spinnerCourses = view.findViewById(R.id.spinner_courses);
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourses);
        
        readDisplayStateValues();

        EditText textNoteTitle = view.findViewById(R.id.text_note_title);
        EditText textNoteText = view.findViewById(R.id.text_note_text);

        displayNote(spinnerCourses, textNoteTitle, textNoteText);
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courceIndex = courses.indexOf(note.getCourse());

        spinnerCourses.setSelection(courceIndex);
        textNoteTitle.setText(note.getTitle());
        textNoteText.setText(note.getText());
    }

    private void readDisplayStateValues() {
        note = (NoteInfo)getActivity().getIntent().getExtras().get(NoteActivity.NOTE_INFO);
    }
}