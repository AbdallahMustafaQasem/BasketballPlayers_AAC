package abdallah.qasem.basketballplayers.view.repositories;


import abdallah.qasem.basketballplayers.view.models.PlayersData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayersRepositories {



    private PlayersApi newsApi;

    private OperationCallBack operationCallBack;


    public PlayersRepositories() {
        newsApi = RetrofitService.cteateService(PlayersApi.class);
    }



    public void getPlayersData(final int page, String per_page, OperationCallBack callBack) {

        this.operationCallBack = callBack;
        if (operationCallBack!=null)
        operationCallBack.startLoading(page);

        newsApi.getList(page, per_page).enqueue(new Callback<PlayersData>() {
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



