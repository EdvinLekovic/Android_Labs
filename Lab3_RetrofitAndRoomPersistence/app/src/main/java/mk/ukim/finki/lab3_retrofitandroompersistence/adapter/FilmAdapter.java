package mk.ukim.finki.lab3_retrofitandroompersistence.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


import mk.ukim.finki.lab3_retrofitandroompersistence.R;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private List<Film> filmList;
    private FilmClickListener filmClickListener;

    public FilmAdapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filmlist_row,null);
        return new FilmViewHolder(view,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.bindHolder(filmList.get(position),filmClickListener);
    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    static class FilmViewHolder extends RecyclerView.ViewHolder {


        TextView filmId,filmTitle,filmYearRelease;
        ImageView imageView;
        ViewGroup parent;
        View view;

        public FilmViewHolder(@NonNull View itemView,ViewGroup parent) {
            super(itemView);
            view = itemView;
            filmId = itemView.findViewById(R.id.filmId);
            filmTitle = itemView.findViewById(R.id.filmTitle);
            filmYearRelease = itemView.findViewById(R.id.filmYearRelease);
            imageView = itemView.findViewById(R.id.imageView);
            this.parent = parent;
        }

        void bindHolder(final Film film,final FilmClickListener filmClickListener){
            filmId.setText(String.valueOf(film.getImdbID()));
            filmTitle.setText(film.getTitle());
            filmYearRelease.setText(film.getYear());
            Glide.with(parent.getContext()).load(film.getPoster()).into(imageView);
            view.setOnClickListener(v -> filmClickListener.onFilmClicked(getAdapterPosition()));
        }
    }

    public void updateData(Film film){
        filmList.add(film);
        this.notifyDataSetChanged();
    }


    public interface FilmClickListener {
        void onFilmClicked(int position);
    }

    public void setFilmClickListener(FilmClickListener filmClickListener) {
        this.filmClickListener = filmClickListener;
    }

    public List<Film> getFilmList() {
        return filmList;
    }
}
