package abdallah.qasem.basketballplayers.view.room.view;

import androidx.databinding.BindingAdapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;



import abdallah.qasem.basketballplayers.view.room.models.Note;

public class CustomNoteListBinding
{

    @BindingAdapter("data")
    public static void setAdapterDate(RecyclerView view, List<Note> itemList) {
        NoteListsAdapter adapter = (NoteListsAdapter) view.getAdapter();
        if (itemList != null) {
                adapter.setItems(itemList);

        }
    }


}
