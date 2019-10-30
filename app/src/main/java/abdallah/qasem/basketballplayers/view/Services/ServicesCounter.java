package abdallah.qasem.basketballplayers.view.Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;

import android.os.IBinder;
import android.util.Log;


import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


public class ServicesCounter extends IntentService {


    public static volatile boolean shouldContinue = true;


    private Thread mythread;
    private boolean running;
    private static final String TAG = ServicesCounter.class.getSimpleName();


    public ServicesCounter() {
        super(ServicesCounter.class.getName());
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e ( TAG," onStartCommand  ");
        mythread = new Thread(new Runnable() {
            public void run(){

                for (int i = 0; i < 10; i++) {
                    sendMessageToActivity(i);
                }
            }
        });

        mythread.start();
/*
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
        }, 1000);*/
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

        Log.e ( TAG,"  onBind ");


        return null;

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.e ( TAG," onHandleIntent  ");
    }







    }

