package com.harshshende.checkmate.Database;

import static androidx.room.OnConflictStrategy.REPLACE;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.harshshende.checkmate.Models.Notes;

import java.util.List;

@Dao
public interface MainDataAccessObject {
    //Method to insert data into room database
    @Insert(onConflict=REPLACE)
    void insert(Notes n01);
    //Method to read all data from room database
    @Query("SELECT * FROM notes ORDER BY ID DESC")
    List<Notes> getAllNotes();
    //Method to update all data of room database
    @Query("UPDATE notes SET title=:title,content=:content WHERE ID=:id")
    void update(int id,String title,String content);
    //Method to delete a note from room database
    @Delete
    void delete(Notes n01);
    //Method to change pinned status of a note
    @Query("UPDATE notes SET pinnedOrNot=:pin WHERE ID=:id")
    void pin(int id,boolean pin);
}
