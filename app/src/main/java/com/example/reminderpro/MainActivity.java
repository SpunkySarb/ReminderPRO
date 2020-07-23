package com.example.reminderpro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Database;
import androidx.room.Room;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    ArrayList<String> data = new ArrayList<>();
    ArrayList<Integer> random = new ArrayList<>();
  Context c = this;
    public static ListView list;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        TextView txt = (TextView) findViewById(R.id.textView);

        NoteDatabase db = Room.databaseBuilder(c, NoteDatabase.class, "StickyNotes").enableMultiInstanceInvalidation().allowMainThreadQueries().build();

        for (int i = 0; i < db.NoteDAO().getAll().size(); i++) {
            String cc = db.NoteDAO().getAll().get(i).note;
            data.add(db.NoteDAO().getAll().get(i).note);


        }







        list = (ListView) findViewById(R.id.list);
        final ChatAdapter adapter = new ChatAdapter(this);
        list.setAdapter(adapter);
        list.setDivider(new ColorDrawable(Color.parseColor("#66000000")));
        list.setDividerHeight(120);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, editNotes.class);

                String note = data.get(position);
                intent.putExtra("note", note);
                intent.putExtra("pos", position);
                startActivity(intent);
finish();
            }


        });


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent i = new Intent(MainActivity.this, AddNotes.class);
                startActivity(i);
                finish();
            }
        });



    }







    private class ChatAdapter extends ArrayAdapter<String> {
        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return data.size();
        }

        public String getItem(int position) {
            return data.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                        Activity.LAYOUT_INFLATER_SERVICE);

                if (position%7 == 0){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout1, null);

                } else if (position%7 == 1){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout2, null);

                } else if (position%7 == 2){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout3, null);

                } else if (position%7 == 3){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout4, null);

                }else if (position%7 == 4){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout5, null);

                }else if (position%7 == 5){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout6, null);

                }else if (position%7 == 6){
                    // set convertView Background
                    convertView = inflater.inflate(R.layout.itemlayout7, null);

                }
            }


            TextView message = (TextView) convertView.findViewById(R.id.textView);
            message.setText(data.get(position).toString());
            return convertView;
        }


    }



}