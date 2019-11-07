package abdallah.qasem.basketballplayers.view.workManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import abdallah.qasem.basketballplayers.R;

public class workerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);


        //One Time Work Request

      /* OneTimeWorkRequest refreshWork =
                new OneTimeWorkRequest.Builder(RefreshDataWorker.class)
                        .build();*/


        PeriodicWorkRequest refreshWork =
                new PeriodicWorkRequest.Builder(RefreshDataWorker.class, 15, TimeUnit.MINUTES)
                        .build();

        WorkManager.getInstance().enqueue(refreshWork);



    }
}
