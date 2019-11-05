package abdallah.qasem.basketballplayers.view.twoWayBinding;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import java.util.Random;
import abdallah.qasem.basketballplayers.R;



public class customBindingMethod  {
    private static final String TAG = customBindingMethod.class.getName();

    @BindingAdapter(value = {"email", "name"}, requireAll = false)
    public static void setAttachedProgressBar(TextView textView, String email, String name) {

        if (email != null & name != null) {
            textView.setText(" Email  is :  " + email + "   Name is : " + name);

        }
    }

    @BindingAdapter({"randomBackground"})
    public static void setRandomBackground(ImageView textView, String input) {
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        textView.setBackgroundColor(color);
    }

    @BindingAdapter("onClick")
    public static void buttonClickListener(final Button button, final TwoWayViewModel viewModel) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (button.getId()) {
                    case R.id.mul_button:
                    viewModel.getMUL();
                }

            }
        });
    }



    @BindingAdapter({"test"})
    public static void testAdapter(View view, ITest test){
    }








}
