package mk.ukim.finki.lab2_fragmentsanddialog;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import mk.ukim.finki.lab2_fragmentsanddialog.data.FakeApi;
import mk.ukim.finki.lab2_fragmentsanddialog.data.model.Film;

public class MovieDetailsFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView filmId = view.findViewById(R.id.filmId);
        TextView filmName = view.findViewById(R.id.nameFilm);
        TextView filmDirector = view.findViewById(R.id.filmDirector);
        TextView filmDescription = view.findViewById(R.id.filmDescription);
        TextView filmActors = view.findViewById(R.id.filmActors);

        int filmPosition = getArguments().getInt("student_num");

        Film currentFilm = FakeApi.getInstance().getFilms().get(filmPosition);
        filmId.setText("FilmID:\n"+String.valueOf(currentFilm.getId()));
        filmName.setText("Film name:\n"+currentFilm.getName());
        filmDirector.setText("Director:\n"+currentFilm.getDirector());
        filmDescription.setText("Film description:\n"+currentFilm.getDescription());
        filmActors.append("Actors:\n\n");
        for(int i = 0;i<currentFilm.getActors().size();i++){
            String actor = currentFilm.getActors().get(i);
            filmActors.append(actor+"\n\n\n");
        }
    }
}