package abdallah.qasem.basketballplayers.view.room.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityNoteListBinding;
import abdallah.qasem.basketballplayers.view.room.models.Note;

public class NoteListActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;
    ActivityNoteListBinding binding;
    private NoteListViewModel viewModel;
    LinearLayoutManager linerLayoutManager;

    NoteListsAdapter noteListsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_list);

        viewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);
        binding.setVm(viewModel);
        binding.setLifecycleOwner(this);


        viewModel = ViewModelProviders.of(this).get(NoteListViewModel.class);

        initRecyclerView();
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteListActivity.this, AddEditNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(noteListsAdapter.getNoteAt(viewHolder.getAdapterPosition()));
                Toast.makeText(NoteListActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(binding.recycler);


        noteListsAdapter.setOnItemClickListener(new NoteListsAdapter.OnItemClickListener() {
                                                    @Override
                                                    public void onItemClick(Note note) {
                                                        Intent intent = new Intent (NoteListActivity.this, AddEditNoteActivity.class);
                                                        intent.putExtra(AddEditNoteActivity.EXTRA_ID, note.getId());
                                                        intent.putExtra(AddEditNoteActivity.EXTRA_TITLE, note.getTitle());
                                                        intent.putExtra(AddEditNoteActivity.EXTRA_DESCRIPTION, note.getDescription());
                                                        intent.putExtra(AddEditNoteActivity.EXTRA_PRIORITY, note.getPriority());
                                                        startActivityForResult(intent, EDIT_NOTE_REQUEST);
                                                    }
                                                }
        );
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            viewModel.insert(note);

            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK) { int id = data.getIntExtra(AddEditNoteActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String title = data.getStringExtra(AddEditNoteActivity.EXTRA_TITLE);
            String description = data.getStringExtra(AddEditNoteActivity.EXTRA_DESCRIPTION);
            int priority = data.getIntExtra(AddEditNoteActivity.EXTRA_PRIORITY, 1);

            Note note = new Note(title, description, priority);
            note.setId(id);
            viewModel.update(note);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_notes:
                viewModel.deleteAllNotes();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void initRecyclerView() {

        noteListsAdapter = new NoteListsAdapter();
        linerLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(linerLayoutManager);
        binding.recycler.setAdapter(noteListsAdapter);
    }

}
