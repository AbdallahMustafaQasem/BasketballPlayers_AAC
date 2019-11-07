package abdallah.qasem.basketballplayers.view.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class Item extends BaseObservable  {


    private  String Name;
    private  String Email;


    public Item(String name, String email) {
        Name = name;
        Email = email;
    }


    @Bindable
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getEmail() {
        return Email;

    }

    public void setEmail(String email)
    {
        Email = email;
        notifyPropertyChanged(BR.email);
    }


}
