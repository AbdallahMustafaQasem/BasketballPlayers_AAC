package abdallah.qasem.basketballplayers.view.room.dataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import abdallah.qasem.basketballplayers.view.room.models.Note;


@Database(entities = {Note.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {


    // marking the instance as volatile to ensure atomic access to the variable
    private static NotesDatabase INSTANCE;

    public abstract NoteDao noteDao();

    public static NotesDatabase getInstance(Context context) {

        {
            synchronized (NotesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NotesDatabase.class, "note_database")
                            // Wipes and rebuilds instead of migrating if no Migration object.
                            // Migration is not part of this codelab.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsync(INSTANCE).execute();


        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // If you want to keep the data through app restarts,
            // comment out the following line.


        }
    };



    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final NoteDao mDao;

        PopulateDbAsync(NotesDatabase db) {
            mDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.

           mDao.deleteAllNots();

            Note note = new Note( "note title ", " note description",2);
            mDao.insert(note);
            Note note1 = new Note( "note title 1 ", " note description1",4);
            mDao.insert(note1);
            return null;
        }
    }

}
