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

public class AddEditNoteActivity extends AppCompatActivity {


    public static final String EXTRA_ID = "AddEditNoteActivity.EXTRA_ID" ;

    public static final String EXTRA_TITLE =
            "AddEditNoteActivity.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "AddEditNoteActivity.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "AddEditNoteActivity.EXTRA_PRIORITY";

    ActivityAddNoteBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_note);


        // Binding Adapter
        binding.numberPickerPriority.setMaxValue(10);
        binding.numberPickerPriority.setMinValue(1);




        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);


        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Note");
            binding.editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            binding.editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            binding.numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
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

        String title =  binding.editTextTitle.getText().toString();
        String description = binding.editTextDescription.getText().toString();
        int priority = binding.numberPickerPriority.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();


    }
}
