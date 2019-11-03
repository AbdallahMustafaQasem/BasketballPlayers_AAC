package abdallah.qasem.basketballplayers.view.ProducerConsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.view.ReentrantLock.CounterActivity;

import static java.lang.Thread.sleep;

public class PC_Activity extends AppCompatActivity {

    private static final String TAG = CounterActivity.class.getSimpleName();

    static Object key = new Object();
    private static boolean[] buffer;
    private static int currentSize;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_);


        buffer = new boolean[10];
        currentSize = 0;


        final Producer producer = new Producer();
        final Consumer consumer = new Consumer();



        Runnable prodRunn = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"Produced...");
                for (int x = 0; x < 100; x++) {
                    producer.produce();
                }

            }
        };

        Runnable consRunn = new Runnable() {
            @Override
            public void run() {
                Log.e(TAG,"Consumer...");
                for (int x = 0; x < 100; x++) {
                    consumer.consume();
                }

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


                if (currentSize == buffer.length) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                buffer[currentSize++] = true;
                Log.e(TAG , "produce "+currentSize +"true");
                key.notifyAll();
            }
        }
    }

    static class Consumer {
        void consume() {
            synchronized (key) {

                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (currentSize == 0) {
                    try {
                        key.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                buffer[--currentSize] = false;
                Log.e(TAG , "consume  "+currentSize +"false");
                key.notifyAll();
            }
        }
    }
}
