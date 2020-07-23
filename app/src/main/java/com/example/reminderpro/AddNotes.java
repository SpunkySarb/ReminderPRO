package com.example.reminderpro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_notes);
        getSupportActionBar().hide();
       final NoteDatabase db = Room.databaseBuilder(getApplicationContext(), NoteDatabase.class, "StickyNotes").enableMultiInstanceInvalidation().allowMainThreadQueries().build();

        Button add = (Button) findViewById(R.id.save2);
        Button cancel = (Button) findViewById(R.id.back);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent intent = new Intent( AddNotes.this, MainActivity.class);
                startActivity(intent);
                finish();
                //  db.NoteDAO().insertData(obj);

                // Do something in response to button click
            }

        });

        ///////////////////////////////////////////////////////////////////////////////////////add DATA
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText note = (EditText) findViewById(R.id.editTextTextMultiLine2);
                NoteDatabase.StickyNotes obj = new NoteDatabase.StickyNotes();
                Toast toast=Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_SHORT);
                toast.show();
                obj.setNote(note.getText().toString());
db.NoteDAO().insertData(obj);
                Intent intent = new Intent( AddNotes.this, MainActivity.class);
                startActivity(intent);
finish();
              //  db.NoteDAO().insertData(obj);

                // Do something in response to button click
            }

        });


    }

    public void onBackPressed() {
        Intent intent = new Intent( AddNotes.this, MainActivity.class);
        startActivity(intent);
finish();

        return;
    }


}