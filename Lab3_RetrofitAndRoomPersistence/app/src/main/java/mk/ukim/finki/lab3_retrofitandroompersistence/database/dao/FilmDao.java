package mk.ukim.finki.lab3_retrofitandroompersistence.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import mk.ukim.finki.lab3_retrofitandroompersistence.FirstFragment;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;

@Dao
public abstract class FilmDao {

    @Transaction
    @Query("SELECT * FROM Film WHERE imdbID = :imdbID")
    public abstract Film getFilm(String imdbID);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insertFilm(Film film);
}
