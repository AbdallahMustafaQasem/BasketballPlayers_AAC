package abdallah.qasem.basketballplayers.view.room.dataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import abdallah.qasem.basketballplayers.view.room.models.Note;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("Delete From note_table")
    void deleteAllNots();


    @Query ("SELECT * From note_table ORDER BY priority DESC")
    LiveData<List<Note>> getALLNotesDESC();
}
