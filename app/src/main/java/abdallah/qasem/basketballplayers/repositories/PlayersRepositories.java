package abdallah.qasem.basketballplayers.repositories;



import androidx.lifecycle.AndroidViewModel;

import abdallah.qasem.basketballplayers.models.PlayersData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersRepositories {
    private static final String TAG = PlayersRepositories.class.getName();

    private static PlayersRepositories instance;
    private PlayersApi newsApi;

    OperationCallBack operationCallBack;

    public static PlayersRepositories getInstance() {
        if (instance == null) {
            instance = new PlayersRepositories();
        }
        return instance;
    }

    private PlayersRepositories() {
        newsApi = RetrofitService.cteateService(PlayersApi.class);
    }


    public void getPlayersData(final int page, String per_page, OperationCallBack callBack) {


        this.operationCallBack = callBack;
        if (operationCallBack!=null)
        operationCallBack.startLoading(page);

        newsApi.getNewsList(page, per_page).enqueue(new Callback<PlayersData>() {
            @Override
            public void onResponse(Call<PlayersData> call, Response<PlayersData> response) {
                if (response.isSuccessful()) {
                    if (operationCallBack!=null)
                    operationCallBack.onSuccess(response.body(),page);

                }else
                if (operationCallBack!=null)
                    operationCallBack.onError();
            }

            @Override
            public void onFailure(Call<PlayersData> call, Throwable t) {


                if (operationCallBack!=null)
                    operationCallBack.onError();
            }
        });

    }


}



