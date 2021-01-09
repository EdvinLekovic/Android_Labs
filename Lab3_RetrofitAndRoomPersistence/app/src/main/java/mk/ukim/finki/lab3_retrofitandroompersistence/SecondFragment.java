package mk.ukim.finki.lab3_retrofitandroompersistence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;

import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;
import mk.ukim.finki.lab3_retrofitandroompersistence.view_model.FirstFragmentViewModel;
import mk.ukim.finki.lab3_retrofitandroompersistence.view_model.SecondFragmentViewModel;

public class SecondFragment extends Fragment {

    private SecondFragmentViewModel secondFragmentViewModel;
    private TextView filmId;
    private TextView filmTitle;
    private TextView filmYearRelease;
    private TextView filmDirector;
    private TextView filmCountry;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        secondFragmentViewModel = ViewModelProviders.of(this).get(SecondFragmentViewModel.class);

        secondFragmentViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Film>() {
            @Override
            public void onChanged(Film film) {
                displayData(film);
            }
        });

        String film = getArguments().getString("imdbID");
        secondFragmentViewModel.loadDataFromDatabase(film);
         filmId = view.findViewById(R.id.filmId);
         filmTitle = view.findViewById(R.id.filmTitle);
         filmYearRelease = view.findViewById(R.id.filmYearRelease);
         filmDirector = view.findViewById(R.id.filmDirector);
         filmCountry = view.findViewById(R.id.filmCountry);

    }

    private void displayData(Film film) {
        filmId.setText(String.valueOf(film.getImdbID()));
        filmTitle.setText(film.getTitle());
        filmYearRelease.setText(film.getYear());
        filmDirector.setText(film.getDirector());
        filmCountry.setText(film.getCountry());
    }
}