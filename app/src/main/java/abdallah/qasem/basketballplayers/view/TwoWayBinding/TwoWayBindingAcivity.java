package abdallah.qasem.basketballplayers.view.TwoWayBinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityTwoWayBindingAcivityBinding;
import abdallah.qasem.basketballplayers.models.Item;

public class TwoWayBindingAcivity extends AppCompatActivity {



ActivityTwoWayBindingAcivityBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_two_way_binding_acivity);


        Item item = new Item("  abdallah qasem "   , " abdullah.q");


binding.setItem(item);
        /*



        * */
    }
}