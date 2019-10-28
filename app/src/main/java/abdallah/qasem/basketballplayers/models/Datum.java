
package abdallah.qasem.basketballplayers.models;

import android.view.View;
import android.widget.ImageView;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.picasso.Picasso;

import abdallah.qasem.basketballplayers.R;
import abdallah.qasem.basketballplayers.helpers.ColorsHelper;
import abdallah.qasem.basketballplayers.view.adapters.PlayersAdapter;

public class Datum {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("height_feet")
    @Expose
    private Object heightFeet;
    @SerializedName("height_inches")
    @Expose
    private Object heightInches;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("position")
    @Expose
    public String position;
    @SerializedName("team")
    @Expose
    public Team team;
    @SerializedName("weight_pounds")
    @Expose
    private Object weightPounds;

    public Datum(String name, int i) {
        this.firstName = name;
        this.id = i;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Object getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Object heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Object getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Object heightInches) {
        this.heightInches = heightInches;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Object getWeightPounds() {
        return weightPounds;
    }

    public void setWeightPounds(Object weightPounds) {
        this.weightPounds = weightPounds;
    }


    @BindingAdapter("android:imageUrl")
    public static void loadImage(View view, int id) {
        ImageView imageView = (ImageView) view;
        String ImageURL = "https://cdn2.thecatapi.com/images/" + id + ".jpg";
        Picasso.get().load(ImageURL).error(R.mipmap.ic_launcher).into(imageView);

     /*   RecyclerView recyclerView = new RecyclerView();
        PlayersAdapter adapter = (PlayersAdapter)recyclerView.getAdapter();
        adapter.setItems();*/

    }


    @BindingAdapter("android:itemColor")
    public static void setColor(View view, String position) {
        view.setBackgroundColor(ContextCompat.getColor(view.getContext(), ColorsHelper.getInstance().getColor(position)));

    }







}
