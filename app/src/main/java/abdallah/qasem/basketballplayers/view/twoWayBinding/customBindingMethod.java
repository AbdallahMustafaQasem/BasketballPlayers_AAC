package abdallah.qasem.basketballplayers.view.twoWayBinding;


import android.graphics.Color;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import java.util.Random;


public class customBindingMethod {


    private static final String TAG = customBindingMethod.class.getName();


    @BindingAdapter(value = {"email","name"},requireAll = false)
    public static void setAttachedProgressBar(TextView textView, String email , String name) {

        textView.setText(" Email  is :  " + email  + "   Name is : "+name);

    }

    @BindingAdapter({"randomBackground"})
    public static void setRandomBackground(ImageView textView,@NonNull String input) {

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        textView.setBackgroundColor(color);


    }
}
