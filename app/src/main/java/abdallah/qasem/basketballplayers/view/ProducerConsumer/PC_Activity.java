package abdallah.qasem.basketballplayers.view.ProducerConsumer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import abdallah.qasem.basketballplayers.R;

public class PC_Activity extends AppCompatActivity {


    private Object lock = new Object();

    private static final int BUFFER_MAX_SIZE = 10;
    String[] buffer = new String[BUFFER_MAX_SIZE];
    int currentSize = 0;


    int added_postion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_);

        produce();
        produce();
        consume();
        produce();
        produce();
        produce();
        consume();
        produce();
        produce();
        produce();
        consume();
        produce();
        produce();
        produce();
        consume();
        produce();
        produce();
        produce();
        consume();
        produce();
        produce();
        produce();
        consume();
        produce();

    }


    public void consume() {

        synchronized (lock) {
            while (currentSize > 0) {
                currentSize--;
                Log.e("  currentSize ", " consume  " +currentSize + " =" + buffer[(currentSize)]);
                buffer[(currentSize)] = "empty";




            }
        }
    }

    public void produce() {
        synchronized (lock) {
            while (currentSize < (BUFFER_MAX_SIZE )) {
                buffer[(currentSize)] = "position " + added_postion;
                Log.e("  currentSize ", " produce "+currentSize+"  add position " + added_postion + " ");
                ++added_postion;
                ++currentSize;

            }
        }
    }
}
