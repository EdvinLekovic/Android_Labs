package mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.view_holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import mk.ukim.finki.lab2_fragmentsanddialog.R;
import mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.FilmAdapter;
import mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.listener.FilmClickListener;
import mk.ukim.finki.lab2_fragmentsanddialog.data.model.Film;

public class FilmViewHolder extends RecyclerView.ViewHolder {

    TextView filmId,filmName,filmDirector;
    View view;
    public FilmViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        filmId = itemView.findViewById(R.id.filmId);
        filmName = itemView.findViewById(R.id.nameFilm);
        filmDirector = itemView.findViewById(R.id.filmDirector);
    }

    public void bindHolder(final Film film,final FilmClickListener filmClickListener){
        filmId.setText(String.valueOf(film.getId()));
        filmDirector.setText(film.getDirector());
        filmName.setText(film.getName());
        view.setOnClickListener(v -> filmClickListener.onFilmClicked(getAdapterPosition()));
    }
}