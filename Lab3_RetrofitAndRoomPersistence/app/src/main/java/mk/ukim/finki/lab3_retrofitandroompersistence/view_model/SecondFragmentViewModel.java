package mk.ukim.finki.lab3_retrofitandroompersistence.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import mk.ukim.finki.lab3_retrofitandroompersistence.database.AppDatabase;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;

public class SecondFragmentViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private MutableLiveData<Film> mutableLiveData;
    public SecondFragmentViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
        mutableLiveData = new MutableLiveData<>();
    }


    public MutableLiveData<Film> getMutableLiveData() {
        return mutableLiveData;
    }

    public void loadDataFromDatabase(final String id){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Film film = appDatabase.filmDao().getFilm(id);
                mutableLiveData.postValue(film);
            }
        }).start();
    }
}
