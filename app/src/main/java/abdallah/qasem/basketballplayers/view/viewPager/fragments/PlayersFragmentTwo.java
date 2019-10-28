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
import abdallah.qasem.basketballplayers.databinding.PlayersFragmentTwoFragmentBinding;
import abdallah.qasem.basketballplayers.view.adapters.PlayersAdapter;

public class PlayersFragmentTwo extends Fragment {

    PlayersFragmentTwoFragmentBinding binding ;
    private PlayersViewModel mViewModel;



    LinearLayoutManager linerLayoutManager;
    int currentPage = 1;



    public static PlayersFragmentOne newInstance() {
        return new PlayersFragmentOne();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.players_fragment_two_fragment, container, false);
        View view = binding.getRoot();


        initRecyclerView();


        return view;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(PlayersViewModel.class);

        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(this);


        Log.e( "    onActivityCreated  " , "  call  getPage ");
        mViewModel.getPage((currentPage), "20");
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


    private void initRecyclerView() {

        PlayersAdapter playersAdapter = new PlayersAdapter();
        linerLayoutManager = new LinearLayoutManager(getActivity());
        binding.recycler.setLayoutManager(linerLayoutManager);
        binding.recycler.setAdapter(playersAdapter);
    }






}
