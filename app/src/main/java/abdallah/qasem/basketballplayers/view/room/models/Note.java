package abdallah.qasem.basketballplayers.view.room.models;


import android.view.View;
import android.widget.NumberPicker;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import abdallah.qasem.basketballplayers.helpers.ColorsHelper;

@Entity(tableName = "note_table")
public class Note {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String title;

    private String description;

    private int priority;


    public Note() {

    }
    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }




    @BindingAdapter("setValue")
    public static void setValue(NumberPicker  numberPicker, int i) {

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(i);


    }
}
