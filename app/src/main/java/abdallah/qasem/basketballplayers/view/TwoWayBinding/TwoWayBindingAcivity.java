package abdallah.qasem.basketballplayers.view.TwoWayBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityTwoWayBindingAcivityBinding;

public class TwoWayBindingAcivity extends AppCompatActivity {



ActivityTwoWayBindingAcivityBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding_acivity);


        Contact contact = new Contact("  abdallah qasem "   , " abdullah.q");
        binding.setContact(contact);
    }
}
