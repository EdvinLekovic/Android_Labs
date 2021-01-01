package mk.ukim.finki.lab2_fragmentsanddialog.data.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mk.ukim.finki.lab2_fragmentsanddialog.R;
import mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.listener.FilmClickListener;
import mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.view_holder.FilmViewHolder;
import mk.ukim.finki.lab2_fragmentsanddialog.data.model.Film;

public class FilmAdapter extends RecyclerView.Adapter<FilmViewHolder> {

    private List<Film> films;
    private FilmClickListener filmClickListener;
    public FilmAdapter(List<Film> films) {
        this.films = films;
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filmlist_row,null);
        return new FilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.bindHolder(films.get(position),filmClickListener);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void setFilmClickListener(FilmClickListener filmClickListener) {
        this.filmClickListener = filmClickListener;
    }
}
