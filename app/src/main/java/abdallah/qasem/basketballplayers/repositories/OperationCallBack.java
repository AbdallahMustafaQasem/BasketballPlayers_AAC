package abdallah.qasem.basketballplayers.repositories;

public interface OperationCallBack<T> {


    void startLoading(int page );

    void onSuccess(T result,int page);

    void onError();


}