package abdallah.qasem.basketballplayers.view.repositories;



import abdallah.qasem.basketballplayers.view.models.PlayersData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PlayersApi {
    @GET("players/")
    Call<PlayersData> getList(@Query("page") int page,
                              @Query("per_page") String per_page);
}
