package abdallah.qasem.basketballplayers.view.Services;
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
import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityServiceBinding;


public class ServiceActivity extends AppCompatActivity {
    private static final String TAG = ServiceActivity.class.getSimpleName();


    public final static String UpdatesCounter = "UpdatesCounter";
    public final static String number = "Unumber";
    private ActivityServiceBinding binding;
    private Intent serviceIntent;


    //--------------------------------------


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service);

        serviceIntent = new Intent(getApplicationContext(), ServiceBlockThread.class);

        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter(ServiceActivity.UpdatesCounter));


        binding.buStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                switch(ServiceBlockThread.status) {

                    case play:
                        Log.e(TAG, "  play  "  );
                        binding.buStartService.setText(R.string.resume);
                        ServiceBlockThread.pause();
                        break;
                    case pause:
                        Log.e(TAG, "  paused ");
                        ServiceBlockThread.resume();
                        binding.buStartService.setText(R.string.pause);

                        break;
                    case stop:
                        Log.e(TAG, "  running ");
                        startService(serviceIntent);
                        binding.buStartService.setText(R.string.pause);
                        break;
                }


            }
        });
        binding.buStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ServiceBlockThread.stop();
                binding.buStartService.setText(R.string.startservice);
            }
        });



        }





    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra(ServiceActivity.number);
            binding.tvCounter.setText(message);

        }
    };

}
