package abdallah.qasem.basketballplayers.view.ReentrantLock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityTestBinding;
import abdallah.qasem.basketballplayers.view.Services.ServiceActivity;

import static java.lang.Thread.sleep;

public class CounterActivity extends AppCompatActivity {


    private static final String TAG = CounterActivity.class.getSimpleName();


    public static volatile CounterActivity.Status status = CounterActivity.Status.stop;


    boolean paused = true;
    boolean running = false;

    private int currentPosition = 0;

    final Lock lock = new ReentrantLock();
    final Condition pauseLock = lock.newCondition();


    private ActivityTestBinding binding;

    Thread myThread;

    // private Handler handlerCounter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test);


        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter(ServiceActivity.UpdatesCounter));

        binding.buStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch (status) {

                    case play:
                        Log.e(TAG, "  play  ");
                        binding.buStartService.setText(R.string.resume);
                        pause();
                        break;
                    case pause:
                        Log.e(TAG, "  paused ");
                        resume();
                        binding.buStartService.setText(R.string.pause);

                        break;
                    case stop:
                        Log.e(TAG, "  running ");
                        play();
                        currentPosition = 0;
                        // start Thread
                        //startService(serviceIntent);
                        binding.buStartService.setText(R.string.pause);
                        break;
                }


            }
        });
        binding.buStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG, " on stop  status " + status.name());
                stop();
                binding.buStartService.setText(R.string.startservice);
            }
        });


    }


    public void stop() {
        pause();
        running = false;
        status = Status.stop;

    }

    public void pause() {
        paused = true;
    }

    public void resume() {

        lock.lock();
        try {

            paused = false;
            pauseLock.signal();
        } finally {
            lock.unlock();
        }


    }

    private void play() {

        running = true;
        paused = false;
        status = Status.play;

        myThread = new Thread(new Runnable() {
            public void run() {

                lock.lock();
                while (running) {

                    if (!running) {
                        // may have changed while waiting to
                        // synchronize on pauseLock

                        break;
                    }
                    if (paused) {
                        try {
                            status = Status.pause;
                            pauseLock.await();
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
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        sendMessageToActivity(++currentPosition);
                    }


                }

                lock.unlock();

                myThread.interrupt();
                return;

            }
        });

        myThread.start();

    }

    private void sendMessageToActivity(int t) {

        Intent intent = new Intent(ServiceActivity.UpdatesCounter);
        intent.putExtra(ServiceActivity.number, "" + t);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);

    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(ServiceActivity.number);
            binding.tvCounter.setText(message);

        }
    };


    enum Status {
        play,
        pause,
        stop
    }



}
