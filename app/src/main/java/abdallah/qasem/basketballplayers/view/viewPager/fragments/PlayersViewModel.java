package abdallah.qasem.basketballplayers.view.viewPager.fragments;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import abdallah.qasem.basketballplayers.view.models.PlayersData;
import abdallah.qasem.basketballplayers.view.repositories.OperationCallBack;
import abdallah.qasem.basketballplayers.view.repositories.PlayersRepositories;

public class PlayersViewModel extends AndroidViewModel implements  OperationCallBack {




    // response from API
    public MutableLiveData<PlayersData> mPlayersData = new MutableLiveData<>();

    // show and hide progressBar in first page
    public MutableLiveData<Boolean> loadingIndicator = new MutableLiveData<>();

    // flag to avoid multiple request at the same time
    private MutableLiveData<Boolean> canLoadingData = new MutableLiveData<>();


    PlayersRepositories repositories = new PlayersRepositories();



    public PlayersViewModel(@NonNull Application application) {
        super(application);
        // set default value
        loadingIndicator.setValue(false);
        canLoadingData.setValue(true);


    }


    public void getPage(int page, String per_page) {

        if (canLoadingData.getValue())
            repositories.getPlayersData(page, per_page, this);
    }


    @Override
    public void startLoading(int page) {
        if (page == 1)
            loadingIndicator.setValue(true);
        else
            loadingIndicator.setValue(false);
        canLoadingData.setValue(false);

    }

    @Override
    public void onSuccess(Object result, int page) {

        Log.e( "  onSuccess " , "  page    = " +page  + "  res ");
        mPlayersData.setValue((PlayersData) result);

        canLoadingData.setValue(true);
        loadingIndicator.setValue(false);
    }

    @Override
    public void onError() {


    }


}