package mk.ukim.finki.lab3_retrofitandroompersistence.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;


@Entity
public class Film {
    @PrimaryKey
    @NonNull
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("Title")
    private String Title;
    @SerializedName("Year")
    private String Year;
    @SerializedName("Director")
    private String Director;
    @SerializedName("Country")
    private String Country;
    @SerializedName("Poster")
    private String Poster;

    public Film(@NonNull String id, String title, String year, String director, String country, String image) {
        this.imdbID = id;
        this.Title = title;
        this.Year = year;
        this.Director = director;
        this.Country = country;
        this.Poster = image;
    }

    public Film() {
        imdbID = null;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}
