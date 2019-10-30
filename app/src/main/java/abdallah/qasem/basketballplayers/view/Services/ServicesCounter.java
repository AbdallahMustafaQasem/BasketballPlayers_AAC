package abdallah.qasem.basketballplayers.view.Services;

import android.app.Service;
import android.content.Intent;

import android.os.Handler;
import android.os.IBinder;
import android.util.Log;


import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;


public class ServicesCounter extends Service {
    private static final String TAG = ServicesCounter.class.getSimpleName();

    public static volatile boolean shouldContinue = true;
    public static volatile boolean shouldStop = false;
    public static volatile Status status = Status.stop;


    private final Lock lock = new ReentrantLock();
    private Handler handlerCounter;

    private int currentPosition = 0;



    private boolean empty = true;  private String message = " test app  message";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate ");


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.e(TAG, "onStartCommand ");
        shouldStop = false;
        shouldContinue = true;
        status = Status.play;


        Thread myThread = new Thread(new Runnable() {
            public void run() {


                while (!shouldStop) {


                    if (shouldStop) {
                        status = Status.stop;
                        stopSelf();
                        return;
                    }


                    if (shouldContinue) {
                        status = Status.play;
                        currentPosition++;


                        lock.lock();
                        try {


                           sleep(250);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.unlock();
                        }

                        sendMessageToActivity(currentPosition);

                    } else {
                        status = Status.pause;

                    }
                }


                status = Status.stop;
                stopSelf();

            }
        });

        myThread.start();

/*

        handlerCounter = new Handler();
        handlerCounter.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (shouldStop) {
                    status = Status.running;
                    stopSelf();
                    return;
                }
                if (paused) {
                    status = Status.play;
                    currentPosition++;
                    sendMessageToActivity(currentPosition);
                    if (currentPosition < 1000) {
                        handlerCounter.postDelayed(this, 300);
                    }
                } else {
                    status = Status.paused;
                    handlerCounter.postDelayed(this, 300);
                }
            }
        }, 1000);

*/

        return Service.START_NOT_STICKY;
    }

    private void sendMessageToActivity(int t) {

        Intent intent = new Intent(ServiceActivity.UpdatesCounter);
        intent.putExtra(ServiceActivity.number, "" + t);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {


        return null;

    }


    enum Status {
        play,
        pause,
        stop
    }





}

