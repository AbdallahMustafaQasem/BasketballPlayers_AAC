package abdallah.qasem.basketballplayers.view.repositories;

public interface OperationCallBack<T> {


    void startLoading(int page );

    void onSuccess(T result,int page);

    void onError();


}