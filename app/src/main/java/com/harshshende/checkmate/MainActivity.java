package com.harshshende.checkmate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.harshshende.checkmate.Adapters.NotesListAdapter;
import com.harshshende.checkmate.Database.RoomDatabase;
import com.harshshende.checkmate.Models.Notes;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    RecyclerView recyclerHome;
    NotesListAdapter noteList;
    List<Notes> listNote=new ArrayList<>();
    RoomDatabase data;
    FloatingActionButton fabAddNewNote;
    SearchView searchViewHome;
    Notes selectedNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerHome=findViewById(R.id.recyclerHome);
        fabAddNewNote=findViewById(R.id.fabAddNewNote);
        searchViewHome=findViewById(R.id.searchViewHome);
        data=RoomDatabase.getInstance(this);
        listNote=data.mainDataAccessObject().getAllNotes();
        updateRecycler(listNote);
        fabAddNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i01=new Intent(MainActivity.this,NotesTakerActivity.class);
                startActivityForResult(i01,101);
            }
        });
        searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });
    }

    private void filter(String newText) {
        List<Notes> filt=new ArrayList<>();
        for(Notes sing:listNote) {
            if(sing.getTITLE().toLowerCase().contains(newText.toLowerCase()) || sing.getCONTENT().toLowerCase().contains(newText.toLowerCase())) {
                filt.add(sing);
            }
        }
        noteList.filterList(filt);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data01) {
        super.onActivityResult(requestCode,resultCode,data01);
        if(requestCode==101) {
            if(resultCode==Activity.RESULT_OK) {
                Notes newNote=(Notes)data01.getSerializableExtra("Note");
                data.mainDataAccessObject().insert(newNote);
                listNote.clear();
                listNote.addAll(data.mainDataAccessObject().getAllNotes());
                noteList.notifyDataSetChanged();
            }
        } else if(requestCode==102) {
            if(resultCode==Activity.RESULT_OK) {
                Notes newNote=(Notes)data01.getSerializableExtra("Note");
                data.mainDataAccessObject().update(newNote.getID(),newNote.getTITLE(),newNote.getCONTENT());
                listNote.clear();
                listNote.addAll(data.mainDataAccessObject().getAllNotes());
                noteList.notifyDataSetChanged();
            }
        }
    }

    private void updateRecycler(List<Notes> listNote) {
        recyclerHome.setHasFixedSize(true);
        recyclerHome.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        noteList=new NotesListAdapter(MainActivity.this,listNote,NotesClickListener);
        recyclerHome.setAdapter(noteList);
    }

    private final NotesClickListener NotesClickListener= new NotesClickListener() {
        @Override
        public void OnClick(Notes note) {
            Intent i01=new Intent(MainActivity.this,NotesTakerActivity.class);
            i01.putExtra("OldNotes",note);
            startActivityForResult(i01,102);
        }

        @Override
        public void onLongClick(Notes note, CardView card) {
            selectedNote=new Notes();
            selectedNote=note;
            showPopup(card);

        }
    };

    private void showPopup(CardView card) {
        PopupMenu popup=new PopupMenu(this,card);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.pin:
                if(selectedNote.isPINNEDORNOT()) {
                    data.mainDataAccessObject().pin(selectedNote.getID(),false);
                    Toast.makeText(MainActivity.this,"Unpinned!",Toast.LENGTH_SHORT).show();
                } else {
                    data.mainDataAccessObject().pin(selectedNote.getID(),true);
                    Toast.makeText(MainActivity.this,"Pinned!",Toast.LENGTH_SHORT).show();
                }
                listNote.clear();
                listNote.addAll(data.mainDataAccessObject().getAllNotes());
                noteList.notifyDataSetChanged();
                return true;

            case R.id.delete:
                if(selectedNote.isPINNEDORNOT()) {
                    Toast.makeText(MainActivity.this,"Pinned Note Can't Be Deleted!",Toast.LENGTH_SHORT).show();
                } else {
                    data.mainDataAccessObject().delete(selectedNote);
                    listNote.remove(selectedNote);
                    noteList.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this,"Deleted!",Toast.LENGTH_SHORT).show();
                }
                return true;

            default:
                return false;
        }
    }
}