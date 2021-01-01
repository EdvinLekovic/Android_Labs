package mk.ukim.finki.lab2_fragmentsanddialog.data;

import java.util.ArrayList;
import java.util.List;

import mk.ukim.finki.lab2_fragmentsanddialog.data.model.Film;

public class FakeApi {

    private static FakeApi instance;
    private List<Film> films = new ArrayList<Film>();

    private FakeApi(){}

    public static FakeApi getInstance() {
        if(instance==null){
            instance = new FakeApi();
            instance.films.add(new Film(1,"Superman","Superman is going to rescue world","John Johnson",List.of("Riley Alba","Jessica Mocka","Huston Iry")));
            instance.films.add(new Film(2,"Venom","Symbiote come to Earth to destroy humans","Terry Jon",List.of("Tom Hardy","Jessica Mocka","Huston Iry")));
            instance.films.add(new Film(3,"Spiderman","Spiderman is going to rescue world","John Johnson",List.of("Peter Parker","Jessica Mocka","Huston Iry")));
            instance.films.add(new Film(4,"Ironman","Ironman is going to rescue world","John Johnson",List.of("Tony Stark","Jessica Mocka","Huston Iry")));
        }
        return instance;
    }



    public List<Film> getFilms() {
        return films;
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }

    public void removeFilm(Film film){
        this.films.remove(film);
    }
}
