package mk.ukim.finki.lab3_retrofitandroompersistence.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
//import retrofit2.converter.gson.GsonConverterFactory;

public class OmdbApiClient {

    private static OmdbApi omdbApi = null;

    public static OmdbApi getOmdbApiInstance() {
        if(omdbApi ==null){
            omdbApi = new Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OmdbApi.class);
        }
        return omdbApi;
    }
}
