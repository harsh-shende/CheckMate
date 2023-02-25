package com.harshshende.checkmate;

import androidx.cardview.widget.CardView;

import com.harshshende.checkmate.Models.Notes;

public interface NotesClickListener {
    //
    void OnClick(Notes note);
    //
    void onLongClick(Notes note,CardView card);
}
