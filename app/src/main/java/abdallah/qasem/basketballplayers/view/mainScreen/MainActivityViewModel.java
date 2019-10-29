package abdallah.qasem.basketballplayers.view.mainScreen;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import abdallah.qasem.basketballplayers.models.PlayersData;
import abdallah.qasem.basketballplayers.repositories.OperationCallBack;
import abdallah.qasem.basketballplayers.repositories.PlayersRepositories;
import abdallah.qasem.basketballplayers.view.viewPager.PagerActivity;

public class MainActivityViewModel extends AndroidViewModel implements OperationCallBack {

    // response from API
    public MutableLiveData<PlayersData> mPlayersData = new MutableLiveData<>();

    // show and hide progressBar in first page
    public MutableLiveData<Boolean> loadingIndicator = new MutableLiveData<>();

    // flag to avoid multiple request at the same time
    private MutableLiveData<Boolean> canLoadingData = new MutableLiveData<>();



    PlayersRepositories repositories = new PlayersRepositories();


    public MainActivityViewModel(@NonNull Application application) {
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
        mPlayersData.setValue((PlayersData) result);
        canLoadingData.setValue(true);
        loadingIndicator.setValue(false);
    }

    @Override
    public void onError() {


    }



    public void onClick(View view) {

        Log.e( "  VM  " , "   onClick   ") ;
        Context context = view.getContext();
        Intent intent = new Intent(context, PagerActivity.class);
        context.startActivity(intent);
    }




}
