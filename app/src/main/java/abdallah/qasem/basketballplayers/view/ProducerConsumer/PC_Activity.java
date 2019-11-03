package abdallah.qasem.basketballplayers.view.ProducerConsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.view.ReentrantLock.CounterActivity;

import static java.lang.Thread.sleep;

public class PC_Activity extends AppCompatActivity {

    private static final String TAG = CounterActivity.class.getSimpleName();

    static Object key = new Object();

    private static int currentSize;

    static ArrayList<String> MainList = new ArrayList<String>(1000);


    static ArrayList<String> NewList = new ArrayList<String>(1000);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_);


        currentSize = 0;


        final Producer producer = new Producer();
        final Consumer consumer = new Consumer();


        Runnable prodRunn = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Produced...");
                for (int x = 0; x < 1000; x++) {
                    producer.produce();
                }


                Log.e(TAG, "  Main list size = " + MainList.size());
            }
        };

        Runnable consRunn = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "Consumer...");
                for (int x = 0; x < 1000; x++) {
                    consumer.consume();
                }

                Log.e(TAG, "  NewList list size = " + NewList.size());
            }
        };

        Thread prodThread = new Thread(prodRunn);
        Thread consThread = new Thread(consRunn);
        prodThread.start();
        consThread.start();


    }


    static class Producer {
        void produce() {
            synchronized (key) {

                Log.e(TAG, " enter Producer ");
                Log.e(TAG, " currentSize =  " + currentSize + " MainList.size()=" + MainList.size());
                if (currentSize == MainList.size()) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.e(TAG, " enter Producer 1");
                String text = " number " + currentSize;
                MainList.add(text);
                Log.e(TAG, "produce " + text + "true");
                key.notifyAll();
            }
        }
    }

    static class Consumer {
        void consume() {
            synchronized (key) {


                if (currentSize == 0) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                --currentSize;
                NewList.add(MainList.get(currentSize));
                MainList.remove(currentSize);
                Log.e(TAG, "consume  " + currentSize + "false");
                key.notifyAll();
            }
        }
    }
}
