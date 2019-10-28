package abdallah.qasem.basketballplayers.view.viewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityPagerBinding;
import abdallah.qasem.basketballplayers.helpers.DepthPageTransformer;
import abdallah.qasem.basketballplayers.view.viewPager.fragments.PlayersFragmentOne;
import abdallah.qasem.basketballplayers.view.viewPager.fragments.PlayersFragmentTwo;

public class PagerActivity extends AppCompatActivity {


    public  ViewPager viewPager;
    ViewPagerAdapter adapter;

    ActivityPagerBinding binding ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager);

        
        setupViewPager(binding.viewpager);
    }

    private void setupViewPager(ViewPager viewpager) {


        adapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        adapter.addFragment(new PlayersFragmentOne());
        adapter.addFragment(new PlayersFragmentTwo());


        binding.viewpager.setAdapter(adapter);
        binding.viewpager.setPageTransformer(true, new DepthPageTransformer());
        binding.viewpager.setOffscreenPageLimit(2);

    }
}
