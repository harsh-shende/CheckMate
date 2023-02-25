package com.harshshende.checkmate.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.harshshende.checkmate.Models.Notes;
import com.harshshende.checkmate.NotesClickListener;
import com.harshshende.checkmate.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NotesListAdapter extends RecyclerView.Adapter<NotesViewHolder> {
    //Creating variables
    Context cont;
    List<Notes> list;
    NotesClickListener clic;
    //Constructor for NotesClickListener
    public NotesListAdapter(Context cont,List<Notes> list,NotesClickListener clic) {
        this.cont=cont;
        this.list=list;
        this.clic=clic;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        return new NotesViewHolder(LayoutInflater.from(cont).inflate(R.layout.notes_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder,int position) {
        holder.textViewTitle.setText(list.get(position).getTITLE());
        holder.textViewTitle.setSelected(true);
        holder.textViewNotes.setText(list.get(position).getCONTENT());
        holder.textViewDate.setText(list.get(position).getDATES());
        holder.textViewDate.setSelected(true);
        if(list.get(position).isPINNEDORNOT()) {
            holder.imageViewPinIcon.setImageResource(R.drawable.pin_icon);
        } else {
            holder.imageViewPinIcon.setImageResource(0);
        }
        int colourCode=getRandomColour();
        holder.notesContainer.setCardBackgroundColor(holder.itemView.getResources().getColor(colourCode,null));
        holder.notesContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clic.OnClick(list.get(holder.getAdapterPosition()));
            }
        });
        holder.notesContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clic.onLongClick(list.get(holder.getAdapterPosition()),holder.notesContainer);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(List<Notes> filt) {
        list=filt;
        notifyDataSetChanged();
    }

    private int getRandomColour() {
        List<Integer> colourCode=new ArrayList<>();
        colourCode.add(R.color.colour01);
        colourCode.add(R.color.colour02);
        colourCode.add(R.color.colour03);
        colourCode.add(R.color.colour04);
        colourCode.add(R.color.colour05);
        Random rand=new Random();
        int random=rand.nextInt(colourCode.size());
        return colourCode.get(random);
    }
}

class NotesViewHolder extends RecyclerView.ViewHolder {
    //Creating variables
    CardView notesContainer;
    ImageView imageViewPinIcon;
    TextView textViewTitle,textViewNotes,textViewDate;

    public NotesViewHolder(@NonNull View itemView) {
        super(itemView);
        //Binding variables with the components
        notesContainer=itemView.findViewById(R.id.notesContainer);
        textViewTitle=itemView.findViewById(R.id.textViewTitle);
        textViewNotes=itemView.findViewById(R.id.textViewNotes);
        textViewDate=itemView.findViewById(R.id.textViewDate);
        imageViewPinIcon=itemView.findViewById(R.id.imageViewPinIcon);
    }
}
