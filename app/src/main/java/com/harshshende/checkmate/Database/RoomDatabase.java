package com.harshshende.checkmate.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.harshshende.checkmate.Models.Notes;

@Database(entities= Notes.class,version=1,exportSchema=false)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    //String variable for room database
    private static String DATABASE_NAME="CheckMate";
    //Method to create an instance of room database
    private static RoomDatabase database;
    //Method to get an instance of room database
    public synchronized static RoomDatabase getInstance(Context cont) {
        if(database==null) {
            database=Room.databaseBuilder(cont.getApplicationContext(),RoomDatabase.class,DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
    //Method to create an instance of MainDataAccessObject
    public abstract MainDataAccessObject mainDataAccessObject();
}
