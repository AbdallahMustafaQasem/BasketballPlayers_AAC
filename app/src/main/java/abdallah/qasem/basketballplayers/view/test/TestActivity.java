package abdallah.qasem.basketballplayers.view.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.view.Services.ServiceActivity;

public class TestActivity extends AppCompatActivity {


    private static final String TAG = ServiceActivity.class.getSimpleName();

    private int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        inc();

        Log.e(TAG , "   count =   " + count)   ;


    }
    public int inc(){
        synchronized(this){
            return ++count;
        }
    }

}
