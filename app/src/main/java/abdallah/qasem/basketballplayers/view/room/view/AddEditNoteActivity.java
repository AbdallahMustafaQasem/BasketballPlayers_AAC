package abdallah.qasem.basketballplayers.view.room.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityAddNoteBinding;
import abdallah.qasem.basketballplayers.view.room.models.Note;

public class AddEditNoteActivity extends AppCompatActivity {


    public static final String EXTRA_ID = "AddEditNoteActivity.EXTRA_ID" ;

    public static final String EXTRA_TITLE =
            "AddEditNoteActivity.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "AddEditNoteActivity.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "AddEditNoteActivity.EXTRA_PRIORITY";

    ActivityAddNoteBinding binding ;
    Note note = new Note() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);

        binding.setNote(note);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Note");
            note.setId(intent.getIntExtra(EXTRA_ID, -1));
            note.setPriority(intent.getIntExtra(EXTRA_PRIORITY, 1));
            note.setDescription(intent.getStringExtra(EXTRA_DESCRIPTION));
            note.setTitle(intent.getStringExtra(EXTRA_TITLE));
        }else {
            setTitle("Add Note");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void saveNote() {




        if (note.getTitle().trim().isEmpty() || note.getDescription().trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, note.getTitle());
        data.putExtra(EXTRA_DESCRIPTION, note.getDescription());
        data.putExtra(EXTRA_PRIORITY, binding.numberPickerPriority.getValue());


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();


    }
}
