package abdallah.qasem.basketballplayers.view.viewPager.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.PlyaersFragmentBinding;
import abdallah.qasem.basketballplayers.view.adapters.PlayersAdapter;

public class PlayersFragment extends Fragment {


    private PlayersViewModel mViewModel;

    PlyaersFragmentBinding binding;


    PlayersAdapter playersAdapter = new PlayersAdapter();
    LinearLayoutManager linerLayoutManager;
    int currentPage = 1;

    private boolean isAdded = false;

    public static PlayersFragment newInstance() {
        return new PlayersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.plyaers_fragment, container, false);
        View view = binding.getRoot();
        initRecyclerView();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (isAdded()) {
            mViewModel = ViewModelProviders.of(this).get(PlayersViewModel.class);
            binding.setViewModel(mViewModel);
            binding.setLifecycleOwner(this);
            binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    if (dy > 0) //check for scroll down
                    {
                        if ((linerLayoutManager.getChildCount() + linerLayoutManager.findFirstVisibleItemPosition()) >= linerLayoutManager.getItemCount()) {
                            mViewModel.getPage((++currentPage), "20");
                        }
                    }
                }
            });
        }

    }

    private void initRecyclerView() {
        linerLayoutManager = new LinearLayoutManager(getActivity());
        binding.recycler.setLayoutManager(linerLayoutManager);
        binding.recycler.setAdapter(playersAdapter);
    }

    public void getData() {
        if (isAdded()) {
            isAdded = true;

            currentPage = 1;
            playersAdapter.clearAdapterList();
            mViewModel.getPage((currentPage), "20");
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isAdded)
            getData();
    }


}
