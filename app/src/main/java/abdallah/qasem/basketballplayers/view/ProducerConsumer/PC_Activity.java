package abdallah.qasem.basketballplayers.view.ProducerConsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.view.ReentrantLock.CounterActivity;

import static java.lang.Thread.sleep;

public class PC_Activity extends AppCompatActivity {

    private static final String TAG = "PC_Activity";

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    static ArrayList<String> MainList = new ArrayList<String>();
    static ArrayList<String> NewList = new ArrayList<String>();

    private static int producerPortion;
    public static boolean haveMore = true;




    Random random = new Random();
    private int max = 1000;
    private int min= 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_);


        final Runnable prodRunn = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Produced...");

                try {
                    sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int x = 0; x < 1000; x++) {
                    String text = " number " + producerPortion;
                    lock.writeLock().lock();
                    MainList.add(text);
                    Log.e(TAG, "producer  " + text);
                    lock.writeLock().unlock();
                    producerPortion++;

                    try {
                        sleep(random.nextInt(max - min + 1) + min);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }


            }
        };

        Runnable consRunn = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Consumer...");


                while (MainList.size() > 0 || haveMore) {
                    try {
                        sleep(random.nextInt(max - min + 1) + min);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (MainList.size() > 0) {
                        lock.readLock().lock();
                        String item = MainList.get(0);
                        MainList.remove(0);
                        Log.e(TAG, "consume  " + item);
                        lock.readLock().unlock();
                        NewList.add(item);
                    }
                }


            }
        };


        Thread   prodThread = new Thread(prodRunn);
        Thread consThread = new Thread(consRunn);
        prodThread.start();
        consThread.start();



    }


}
