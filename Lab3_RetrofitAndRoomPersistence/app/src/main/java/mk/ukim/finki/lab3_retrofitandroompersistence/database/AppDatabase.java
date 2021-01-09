package mk.ukim.finki.lab3_retrofitandroompersistence.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import mk.ukim.finki.lab3_retrofitandroompersistence.database.dao.FilmDao;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;

@Database(entities = {Film.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract FilmDao filmDao();

    private static volatile AppDatabase appDatabase;

    public static synchronized AppDatabase getInstance(Context context){
        if(appDatabase == null){
            appDatabase = create(context);
        }
        return appDatabase;
    }

    private static AppDatabase create(final Context context){
        return Room.databaseBuilder(context,
                AppDatabase.class, "film.db").build();
    }
}
