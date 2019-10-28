package abdallah.qasem.basketballplayers.view.viewPager;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();

    Context context;

    public ViewPagerAdapter(FragmentManager manager, Context context) {
        super(manager);
        fragmentManager = manager;
        this.context = context;

    }

    private FragmentManager fragmentManager;

    @Override
    public Fragment getItem(int position) {

        mFragmentList.get(position).setHasOptionsMenu(true);
        return mFragmentList.get(position);
    }


    @Override
    public int getCount() {
        return mFragmentList.size();
    }


    public void addFragment(Fragment fragment) {
        mFragmentList.add(fragment);
        fragmentManager.executePendingTransactions();

    }


}
