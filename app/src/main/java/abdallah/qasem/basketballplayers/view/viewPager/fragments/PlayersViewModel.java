package abdallah.qasem.basketballplayers.view.viewPager.fragments;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;


import abdallah.qasem.basketballplayers.models.PlayersData;
import abdallah.qasem.basketballplayers.repositories.OperationCallBack;
import abdallah.qasem.basketballplayers.repositories.PlayersRepositories;

public class PlayersViewModel extends AndroidViewModel implements  OperationCallBack {



    // fragment one --------------------------------------------------------------

    // response from API
    public MutableLiveData<PlayersData> mPlayersData_one = new MutableLiveData<>();

    // show and hide progressBar in first page
    public MutableLiveData<Boolean> loadingIndicator_one = new MutableLiveData<>();

    // flag to avoid multiple request at the same time
    private MutableLiveData<Boolean> canLoadingData_one = new MutableLiveData<>();

    //------------------------------------------------------------------------------


    // fragment two  --------------------------------------------------------------

    // response from API
    public MutableLiveData<PlayersData> mPlayersData_two = new MutableLiveData<>();

    // show and hide progressBar in first page
    public MutableLiveData<Boolean> loadingIndicator_two = new MutableLiveData<>();

    // flag to avoid multiple request at the same time
    private MutableLiveData<Boolean> canLoadingData_two = new MutableLiveData<>();

    //------------------------------------------------------------------------------

    public PlayersViewModel(@NonNull Application application) {
        super(application);
        // set default value
        loadingIndicator_one.setValue(false);
        canLoadingData_one.setValue(true);
        loadingIndicator_two.setValue(false);
        canLoadingData_two.setValue(true);

    }


    public void getPage(int page, String per_page) {

        if (canLoadingData_one.getValue())
            PlayersRepositories.getInstance().getPlayersData(page, per_page, this);
    }


    @Override
    public void startLoading(int page) {
        if (page == 1)
            loadingIndicator_one.setValue(true);
        else
            loadingIndicator_one.setValue(false);
        canLoadingData_one.setValue(false);

    }

    @Override
    public void onSuccess(Object result, int page) {

        Log.e( "  onSuccess " , "  page    = " +page  + "  res ");
        mPlayersData_one.setValue((PlayersData) result);

        canLoadingData_one.setValue(true);
        loadingIndicator_two.setValue(false);
    }

    @Override
    public void onError() {


    }


}