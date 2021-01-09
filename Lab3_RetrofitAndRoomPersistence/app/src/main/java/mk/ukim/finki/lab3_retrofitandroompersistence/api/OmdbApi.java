package mk.ukim.finki.lab3_retrofitandroompersistence.api;

import java.util.Map;

import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface OmdbApi {
    @GET("/")
    Call<Film> getFilm(@Query("t") String title,@Query("apikey") String apikey);
}
