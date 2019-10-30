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


   public  final static String UpdatesCounter = "UpdatesCounter";
    public  final static String number = "Unumber";
    private ActivityServiceBinding binding;
    private Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_service);

        serviceIntent = new Intent(getApplicationContext(), ServicesCounter.class);


        LocalBroadcastManager.getInstance(this).registerReceiver(
                mMessageReceiver, new IntentFilter(ServiceActivity.UpdatesCounter));

        binding.buStartService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(" Activity "  , "   startService   ");
                startService(serviceIntent);

            }
        });
 binding.buStopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {


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
