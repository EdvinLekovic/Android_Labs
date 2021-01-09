package mk.ukim.finki.lab3_retrofitandroompersistence.worker;

import android.os.AsyncTask;

import mk.ukim.finki.lab3_retrofitandroompersistence.database.AppDatabase;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;

public class InsertFilmWorker extends AsyncTask<Film,Void,Void> {

    private AppDatabase appDatabase;

    public InsertFilmWorker(AppDatabase appDatabase) {
        this.appDatabase = appDatabase;
    }


    @Override
    protected Void doInBackground(Film... films) {
        appDatabase.filmDao().insertFilm(films[0]);
        return null;
    }
}
