package abdallah.qasem.basketballplayers.view.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import static java.lang.Thread.sleep;


public class ServiceBlockThread extends Service {
    private static final String TAG = ServiceBlockThread.class.getSimpleName();

    public static volatile Status status = Status.stop;



    public final static String UpdatesCounter = "UpdatesCounter";
    public final static String number = "Unumber";

    public static volatile boolean paused = true;
    public static volatile boolean running = false;
    private static final Object pauseLock = new Object();
    private int currentPosition = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand ");

        running = true;
        paused = false;
        status = Status.play;


        Thread myThread = new Thread(new Runnable() {
            public void run() {

                while (running) {

                        if (!running) {
                            // may have changed while waiting to
                            // synchronize on pauseLock

                            break;
                        }
                        if (paused) {
                            status = Status.pause;
                            try {
                                synchronized (pauseLock) {

                                    pauseLock.wait();
                                }
                            } catch (InterruptedException ex) {
                                break;
                            }
                            if (!running) {
                                // running might have changed since we paused
                                break;
                            }
                        }

                    status = Status.play;
                    try {
                        sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        sendMessageToActivity(++currentPosition);
                    }



                }


                status = Status.stop;
                stopSelf();

            }
        });

        myThread.start();


        return Service.START_NOT_STICKY;
    }


    public static void stop() {
        running = false;
        resume();
    }

    public static void pause() {
        paused = true;
    }

    public static void resume() {
        synchronized (pauseLock) {
            paused = false;
            pauseLock.notifyAll(); // Unblocks thread
        }
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

