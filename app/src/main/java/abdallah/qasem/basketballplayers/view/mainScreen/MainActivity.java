package abdallah.qasem.basketballplayers.view.mainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
// test push

    LinearLayoutManager linerLayoutManager;
    private MainActivityViewModel mMainActivityViewModel;
    private ActivityMainBinding binding;
    int currentPage = 1;

    public static void goToMainActivity(Context mContext) {
        Intent login = new Intent(mContext, MainActivity.class);
        mContext.startActivity(login);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initRecyclerView();

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        binding.setViewModel(mMainActivityViewModel);
        binding.setLifecycleOwner(this);


        mMainActivityViewModel.getPage((currentPage), "20");
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    if ((linerLayoutManager.getChildCount() + linerLayoutManager.findFirstVisibleItemPosition()) >= linerLayoutManager.getItemCount()) {
                        mMainActivityViewModel.getPage((++currentPage), "20");
                    }
                }
            }
        });

    }

    private void initRecyclerView() {

        PlayersAdapter playersAdapter = new PlayersAdapter();
        linerLayoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(linerLayoutManager);
        binding.recycler.setAdapter(playersAdapter);
    }





}
