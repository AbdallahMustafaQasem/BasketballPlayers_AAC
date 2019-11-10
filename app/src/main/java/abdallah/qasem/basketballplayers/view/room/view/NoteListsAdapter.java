package abdallah.qasem.basketballplayers.view.room.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.RowNoteBinding;

import abdallah.qasem.basketballplayers.view.room.models.Note;

public class NoteListsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Object> notesList;
    private static final int TYPE_LIST_ITEM_ = 1;

    private OnItemClickListener listener;


    //  constructor
    public NoteListsAdapter() {
        this.notesList = new ArrayList<>();
    }


    @Override
    public int getItemViewType(int position) {
        if (notesList.get(position) instanceof Note)
            return TYPE_LIST_ITEM_;
        else
            return 0;
    }


    public void setItems(List<Note> data) {
        //add items and notify

        if (data != null) {
            notesList.clear();
            notesList.addAll(data);

            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {

            case TYPE_LIST_ITEM_:
                RowNoteBinding binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                                , R.layout.row_note, parent, false);


                return new NoteViewHolder(binding);


        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


        if (holder instanceof NoteViewHolder) {
            NoteViewHolder viewHolder = (NoteViewHolder) holder;

            final Note note = (Note) notesList.get(position);
            viewHolder.binding.setNote(note);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(note);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public Note getNoteAt(int adapterPosition) {
        return (Note) notesList.get(adapterPosition);
    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {

        RowNoteBinding binding;


        NoteViewHolder(final RowNoteBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
