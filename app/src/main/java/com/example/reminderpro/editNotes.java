package com.example.reminderpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editNotes extends AppCompatActivity {
    public static EditText editNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_notes);
        getSupportActionBar().hide();

         editNote = (EditText) findViewById(R.id.editTextTextMultiLine);
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
        Intent intent = getIntent();
     editNote.setText(intent.getStringExtra("note"));

        Button delete = (Button) findViewById(R.id.button3);
        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final NoteDatabase db = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "StickyNotes").enableMultiInstanceInvalidation().allowMainThreadQueries().build();
                Intent intent = getIntent();
                int ss=   intent.getIntExtra("pos", 0);
                Toast toast=Toast.makeText(getApplicationContext(),"DELETED",Toast.LENGTH_SHORT);
                toast.show();
                Intent nn = getIntent();
          String notte=      nn.getStringExtra("note");
                db.NoteDAO().deleteNote(notte);


                Intent intentss = new Intent( editNotes.this, MainActivity.class);
                startActivity(intentss);
                finish();
                //  db.NoteDAO().insertData(obj);

                // Do something in response to button click
            }

        });

    }

    public void save(View v){

        MainActivity obj = new MainActivity();
        Intent intent = getIntent();
    int ss=   intent.getIntExtra("pos", 0);
        EditText note = (EditText) findViewById(R.id.editTextTextMultiLine);
        NoteDatabase.StickyNotes objs = new NoteDatabase.StickyNotes();
        final NoteDatabase db = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "StickyNotes").enableMultiInstanceInvalidation().allowMainThreadQueries().build();

        Toast toast=Toast.makeText(getApplicationContext(),"UPDATED",Toast.LENGTH_SHORT);
        toast.show();
        String notte=      intent.getStringExtra("note");
        db.NoteDAO().updateNote(notte,note.getText().toString());
        InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        Intent intents = new Intent( editNotes.this, MainActivity.class);
        startActivity(intents);
        finish();

    }
    public void onBackPressed() {
        Intent intent = new Intent( editNotes.this, MainActivity.class);
        startActivity(intent);


        return;
    }
}