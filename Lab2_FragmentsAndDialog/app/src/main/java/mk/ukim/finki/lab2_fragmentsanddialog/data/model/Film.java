package mk.ukim.finki.lab2_fragmentsanddialog.data.model;

import java.util.List;

public class Film {

     private int id;
     private String name;
     private String description;
     private String director;
     private List<String> actors;

    public Film(int id, String name, String description, String director, List<String> actors) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.director = director;
        this.actors = actors;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public List<String> getActors() {
        return actors;
    }
}
