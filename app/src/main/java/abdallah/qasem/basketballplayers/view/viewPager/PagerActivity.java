package abdallah.qasem.basketballplayers.view.viewPager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityPagerBinding;
import abdallah.qasem.basketballplayers.helpers.DepthPageTransformer;
import abdallah.qasem.basketballplayers.view.viewPager.fragments.PlayersFragment;

public class PagerActivity extends AppCompatActivity   {

    private static final String TAG = PagerActivity.class.getName();

    ViewPagerAdapter pagerAdapter;

    ActivityPagerBinding binding;

    PlayersFragment fragment_One = new PlayersFragment();
    PlayersFragment fragment_Two = new PlayersFragment();
    PlayersFragment fragment_Three = new PlayersFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_pager);


        setupViewPager();
    }

    private void setupViewPager() {


        pagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());
        pagerAdapter.addFragment(fragment_One);
        pagerAdapter.addFragment(fragment_Two);
        pagerAdapter.addFragment(fragment_Three);

        binding.viewpager.setAdapter(pagerAdapter);
        binding.viewpager.setPageTransformer(true, new DepthPageTransformer());
        binding.viewpager.setOffscreenPageLimit(0);




        binding.viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("  PagerActivity  ", " onPageScrolled " + position);
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("  PagerActivity  ", " onPageSelected " + position);
                switch (position) {
                    case 0:
                        fragment_One.getData();
                        break;
                    case 1:
                        fragment_Two.getData();
                        break;
                    case 2:
                       fragment_Three.getData();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.e("  PagerActivity  ", " onPageScrollStateChanged " + state);

            }
        });




    }











}


