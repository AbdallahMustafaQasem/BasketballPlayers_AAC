package abdallah.qasem.basketballplayers.view.mainScreen;



import android.view.View;
import android.widget.ProgressBar;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import abdallah.qasem.basketballplayers.models.PlayersData;

public class BindingMainActivity {
    private static final String TAG = BindingMainActivity.class.getName();

    public BindingMainActivity() {
    }

    @BindingAdapter("android:data")
    public static void setAdapterDate(RecyclerView view, PlayersData data) {
        PlayersAdapter adapter = (PlayersAdapter) view.getAdapter();
        if (data != null) {

            if (data.getMeta().getCurrentPage() == 1) {
                if (adapter != null)
                    adapter.setItems(data.getData());
            } else if (adapter != null) {
                adapter.removeLoadingIndicator();
                adapter.setItems(data.getData());
            }
        }
    }

    @BindingAdapter({"android:attachedProgressBar"})
    public static void setAttachedProgressBar(ProgressBar view, boolean loading) {
        if (loading) {
            view.setVisibility(View.VISIBLE);
        }else {
            view.setVisibility(View.GONE);

        }}

}
