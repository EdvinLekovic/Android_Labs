package mk.ukim.finki.lab3_retrofitandroompersistence.view_model;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import mk.ukim.finki.lab3_retrofitandroompersistence.api.OmdbApi;
import mk.ukim.finki.lab3_retrofitandroompersistence.api.OmdbApiClient;
import mk.ukim.finki.lab3_retrofitandroompersistence.database.AppDatabase;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;
import mk.ukim.finki.lab3_retrofitandroompersistence.worker.InsertFilmWorker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstFragmentViewModel extends AndroidViewModel {

    private Application application;
    private AppDatabase appDatabase;
    private OmdbApi omdbApi;
    private InsertFilmWorker insertFilmWorker;
    private MutableLiveData<Film> filmMutableLiveData;

    public FirstFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application =application;
        this.omdbApi = OmdbApiClient.getOmdbApiInstance();
        this.appDatabase = AppDatabase.getInstance(application);
        this.filmMutableLiveData = new MutableLiveData<>();
        insertFilmWorker = new InsertFilmWorker(appDatabase);
    }


    public void searchFilm(String title){
        omdbApi.getFilm(title,"a7d048f3").enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if(response.body()!=null){
                    Film film = response.body();
                    saveInLocalDatabase(film);
                    filmMutableLiveData.setValue(film);
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                System.out.println(call);
                Toast.makeText(application,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void saveInLocalDatabase(Film film){
        //System.out.println(insertFilmWorker.getStatus());
        if(film!=null) {
            new InsertFilmWorker(appDatabase).execute(film);
        }
    }


    public MutableLiveData<Film> getFilmMutableLiveData() {
        return filmMutableLiveData;
    }
}
