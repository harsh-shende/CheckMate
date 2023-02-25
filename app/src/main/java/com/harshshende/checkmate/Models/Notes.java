package com.harshshende.checkmate.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="notes")
public class Notes implements Serializable {
    //ID would be the primary key will be auto-generated
    @PrimaryKey(autoGenerate=true)
    int ID=0;
    //String variable to store the title of a note
    @ColumnInfo(name="title")
    String TITLE="";
    //String variable to store the content of a note
    @ColumnInfo(name="content")
    String CONTENT="";
    //String variable to store the last edit time of a note
    @ColumnInfo(name="dates")
    String DATES="";
    //Boolean variable to check if a note is pinned or unpinned
    @ColumnInfo(name="pinnedOrNot")
    boolean PINNEDORNOT=false;
    //Getter and setter for the ID attribute
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID=ID;
    }
    //Getter and setter for the TITLE attribute
    public String getTITLE() {
        return TITLE;
    }
    public void setTITLE(String TITLE) {
        this.TITLE=TITLE;
    }
    //Getter and setter for the CONTENT attribute
    public String getCONTENT() {
        return CONTENT;
    }
    public void setCONTENT(String CONTENT) {
        this.CONTENT=CONTENT;
    }
    //Getter and setter for the DATES attribute
    public String getDATES() {
        return DATES;
    }
    public void setDATES(String DATES) {
        this.DATES=DATES;
    }
    //Getter and setter for the PINNEDORNOT attribute
    public boolean isPINNEDORNOT() {
        return PINNEDORNOT;
    }
    public void setPINNEDORNOT(boolean PINNEDORNOT) {
        this.PINNEDORNOT=PINNEDORNOT;
    }
}
