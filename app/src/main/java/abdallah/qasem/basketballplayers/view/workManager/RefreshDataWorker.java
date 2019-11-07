package abdallah.qasem.basketballplayers.view.workManager;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import abdallah.qasem.basketballplayers.R;

public class RefreshDataWorker extends Worker {


    public RefreshDataWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Worker.Result doWork() {


        int id =  (int) System.currentTimeMillis();
        showNotification(" test worker"," doWork "+id);

        return Worker.Result.success();
    }


    private void showNotification(String task, String desc) {

        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
       String channelId = "task_channel";
       String channelName = "task_name";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new
                    NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle(task)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher);

        manager.notify((int) System.currentTimeMillis(), builder.build());

    }
}
