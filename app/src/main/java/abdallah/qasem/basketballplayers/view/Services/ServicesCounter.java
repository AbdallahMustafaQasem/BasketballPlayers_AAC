package abdallah.qasem.basketballplayers.view.Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

import android.os.Handler;
import android.os.IBinder;




import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class ServicesCounter extends IntentService {


    int t = 0;
    Handler handlerCounter;

    private static final String TAG = ServicesCounter.class.getSimpleName();


    public ServicesCounter() {
        super(ServicesCounter.class.getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        handlerCounter = new Handler();
        handlerCounter.postDelayed(new Runnable() {
            @Override
            public void run() {
                t++;
                sendMessageToActivity(t);
                if(t<1000) {
                    handlerCounter.postDelayed(this, 300);
                }
            }
        }, 1000);
        return Service.START_NOT_STICKY;
    }

    private void sendMessageToActivity(int t) {

        Intent intent = new Intent(ServiceActivity.UpdatesCounter);
        intent.putExtra(ServiceActivity.number, ""+t);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {




        return null;

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {



    }


    }

