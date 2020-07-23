package com.example.reminderpro;

import androidx.room.Dao;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.RoomDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@androidx.room.Database(entities = {NoteDatabase.StickyNotes.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public abstract NoteDAO NoteDAO();

    @Entity
    public static class StickyNotes {


        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
        @PrimaryKey @NotNull
        public String note;






    }


}

@Dao
interface NoteDAO {
    @Query("SELECT * FROM StickyNotes")
    public List<NoteDatabase.StickyNotes> getAll();

    @Insert
    public void insertData(NoteDatabase.StickyNotes note);

    @Query("DELETE FROM StickyNotes  WHERE note = :txt")
    int deleteNote(String txt);

    @Query("UPDATE StickyNotes SET note = :update WHERE note = :txt")
    int updateNote(String txt, String update);
}