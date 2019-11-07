package abdallah.qasem.basketballplayers.view.room.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import abdallah.qasem.basketballplayers.view.room.models.Note;
import abdallah.qasem.basketballplayers.view.room.repositories.NoteRepo;

public class NoteListViewModel extends AndroidViewModel {


    private NoteRepo repositories;
    private LiveData<List<Note>> allNots;


    public NoteListViewModel(@NonNull Application application) {
        super(application);
        repositories = new NoteRepo(application);
        allNots = repositories.getAllNotes();
    }


    public void insert(Note note) {
        repositories.insert(note);

    }

    public void update(Note note) {
        repositories.update(note);

    }

    public void delete(Note note) {
        repositories.delete(note);

    }

    public void deleteAllNotes() {
        repositories.deleteAllNote();

    }

    public LiveData<List<Note>> getAllNots() {
        return allNots;
    }
}
