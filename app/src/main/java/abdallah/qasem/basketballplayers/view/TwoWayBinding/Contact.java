package abdallah.qasem.basketballplayers.view.TwoWayBinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class Contact extends BaseObservable {


    String Name;
    String Email;


    public Contact(String name, String email) {
        Name = name;
        Email = email;
    }


    @Bindable
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Bindable
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


}
