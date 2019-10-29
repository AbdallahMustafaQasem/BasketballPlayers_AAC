package abdallah.qasem.basketballplayers.view.mainScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.databinding.RowPlayersBinding;

import abdallah.qasem.basketballplayers.models.Datum;
import abdallah.qasem.basketballplayers.models.LoadingItem;


public class PlayersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> itemList;


    private static final int TYPE_LIST_ITEM = 1;
    private static final int TYPE_LOADING = 2;



    public PlayersAdapter () {
        this.itemList = new ArrayList<>();


    }

    @Override
    public int getItemViewType(int position) {

        if (itemList.get(position) instanceof Datum) {
            return TYPE_LIST_ITEM;
        } else if (itemList.get(position) instanceof LoadingItem) {
            return TYPE_LOADING;
        }
        return 0;
    }

    public void setItems(List<Datum> data) {
        //add items and notify
        itemList.addAll(data);
        itemList.add(new LoadingItem());
        notifyDataSetChanged();


    }
    public void removeLoadingIndicator () {
        //add items and notify
        itemList.remove(itemList.size() - 1);
        notifyDataSetChanged();


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view;
        switch (viewType) {

            case TYPE_LIST_ITEM:
                RowPlayersBinding binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext())
                                , R.layout.row_players, parent, false);





                return new PlayerViewHolder(binding);
            case TYPE_LOADING:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_loading_item, parent, false);
                return new LoadingViewHolder(view);

        }

        return null;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof PlayerViewHolder) {
            PlayerViewHolder viewHolder = (PlayerViewHolder) holder;
            Datum datum = (Datum) itemList.get(position);
            viewHolder.binding.setDatum(datum);


        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void clearAdapterList() {
        itemList.clear();
        notifyDataSetChanged();
    }


    public class PlayerViewHolder extends RecyclerView.ViewHolder {

        RowPlayersBinding binding;

        PlayerViewHolder(final RowPlayersBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;

        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        LoadingViewHolder(View view) {
            super(view);

        }
    }
}
