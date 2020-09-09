package com.jwhh.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.jwhh.notekeeper.model.CourseInfo;
import com.jwhh.notekeeper.model.DataManager;
import com.jwhh.notekeeper.model.NoteInfo;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_POSITION = "com.jwhh.notekeeper.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;

    private Spinner spinnerCourses;
    private EditText textNoteTitle;
    private EditText textNoteText;

    private int notePosition;
    private NoteInfo note;
    private boolean isNewNote;

    private boolean isCanceling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readDisplayStateValues();

        spinnerCourses = findViewById(R.id.spinner_courses);
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        ArrayAdapter<CourseInfo> adapterCourses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourses);

        textNoteTitle = findViewById(R.id.text_note_title);
        textNoteText = findViewById(R.id.text_note_text);

        if (!isNewNote) {
            displayNote(spinnerCourses, textNoteTitle, textNoteText);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (isCanceling) {
            if (isNewNote)
                DataManager.getInstance().removeNote(notePosition);
        } else {
            saveNote();
        }
    }

    private void displayNote(Spinner spinnerCourses, EditText textNoteTitle, EditText textNoteText) {
        List<CourseInfo> courses = DataManager.getInstance().getCourses();
        int courceIndex = courses.indexOf(note.getCourse());

        spinnerCourses.setSelection(courceIndex);
        textNoteTitle.setText(note.getTitle());
        textNoteText.setText(note.getText());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
//        note = intent.getParcelableExtra(NoteActivity.NOTE_POSITION);
        int position = intent.getIntExtra(NoteActivity.NOTE_POSITION, NoteActivity.POSITION_NOT_SET);
        isNewNote = position == NoteActivity.POSITION_NOT_SET;
        if (isNewNote) {
            createNewNote();
        } else {
            note = DataManager.getInstance().getNotes().get(position);
        }
    }

    private void saveNote() {
        note.setCourse((CourseInfo)spinnerCourses.getSelectedItem());
        note.setTitle(textNoteTitle.getText().toString());
        note.setText(textNoteText.getText().toString());
    }

    private void createNewNote() {
        DataManager dm = DataManager.getInstance();
        notePosition = dm.createNewNote();
        note = dm.getNotes().get(notePosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_mail) {
            sendEmail();
            return true;
        } else if (id == R.id.action_cancel) {
            isCanceling = true;
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendEmail() {
        CourseInfo course = (CourseInfo) spinnerCourses.getSelectedItem();

        String subject = textNoteTitle.getText().toString();
        String text = "Checkout what I learned in the Pluralsight course \"" +
                course.getTitle() + "\"\n" + textNoteText.getText();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}