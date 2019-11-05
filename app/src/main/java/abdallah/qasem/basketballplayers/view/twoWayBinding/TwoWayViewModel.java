package abdallah.qasem.basketballplayers.view.twoWayBinding;

import android.app.Application;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import abdallah.qasem.basketballplayers.view.mainScreen.MainActivity;

public class TwoWayViewModel extends AndroidViewModel {



    public MutableLiveData<NumberModel> NumberModelMutableLiveData = new MutableLiveData<>();



    public MutableLiveData<Integer> SUM_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> DIV_MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Integer> MUL_MutableLiveData = new MutableLiveData<>();

    public TwoWayViewModel(@NonNull Application application) {
        super(application);
    }



    // get value for first time
    public void getFirstNumberFtomDataBase() {
        NumberModelMutableLiveData.setValue(getNumber());
    }



    public void getSUM() {
        SUM_MutableLiveData.setValue(getNumber().getFirstNum() + getNumber().getSecondNum());
    }

    public void getDIV() {
        DIV_MutableLiveData.setValue(getNumber().getFirstNum() / getNumber().getSecondNum());
    }

    public void getMUL() {
        MUL_MutableLiveData.setValue(getNumber().getFirstNum() * getNumber().getSecondNum());
    }


    public void showToast(View view) {
        Toast.makeText(view.getContext(), "My toast message",Toast.LENGTH_SHORT).show();
    }

    public void openAcivity(View view) {

        MainActivity.goToMainActivity(view.getContext());

    }


    // get value from database
    private NumberModel getNumber() {

        return new NumberModel(40, 20);
    }
}
