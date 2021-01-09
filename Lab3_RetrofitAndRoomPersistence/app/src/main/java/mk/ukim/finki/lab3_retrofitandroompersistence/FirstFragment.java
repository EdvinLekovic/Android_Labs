package mk.ukim.finki.lab3_retrofitandroompersistence;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mk.ukim.finki.lab3_retrofitandroompersistence.adapter.FilmAdapter;
import mk.ukim.finki.lab3_retrofitandroompersistence.model.Film;
import mk.ukim.finki.lab3_retrofitandroompersistence.view_model.FirstFragmentViewModel;

public class FirstFragment extends Fragment {

    private EditText titleOfFilm;
    private RecyclerView filmList;
    private FirstFragmentViewModel firstFragmentViewModel;
    private FilmAdapter filmAdapter;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        firstFragmentViewModel = ViewModelProviders.of(this).get(FirstFragmentViewModel.class);

        firstFragmentViewModel.getFilmMutableLiveData().observe(getViewLifecycleOwner(), new Observer<Film>() {
            @Override
            public void onChanged(Film film) {
                displayData(film);
            }
        });

        titleOfFilm = (EditText) view.findViewById(R.id.titleOfFilm);
        filmList = (RecyclerView) view.findViewById(R.id.filmList);
        filmAdapter = new FilmAdapter(new ArrayList<Film>());

        filmAdapter.setFilmClickListener(new FilmAdapter.FilmClickListener() {

            @Override
            public void onFilmClicked(int position) {
                Bundle bundle = new Bundle();
                Film film = filmAdapter.getFilmList().get(position);
                bundle.putString("imdbID", film.getImdbID());
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, bundle);
            }
        });



        titleOfFilm.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE){
                    String title = titleOfFilm.getText().toString();
                    if(!title.isEmpty()){
                        firstFragmentViewModel.searchFilm(title);
                    }
                    else {
                        Toast.makeText(getContext(),"Error please enter the title of the film",Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;
            }
        });
        filmList.setLayoutManager(new LinearLayoutManager(getContext()));
        filmList.setAdapter(filmAdapter);
    }



    private void displayData(final Film film){
        filmAdapter.updateData(film);
    }
}