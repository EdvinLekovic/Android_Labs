package mk.ukim.finki.lab2_fragmentsanddialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import mk.ukim.finki.lab2_fragmentsanddialog.data.FakeApi;
import mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.FilmAdapter;
import mk.ukim.finki.lab2_fragmentsanddialog.data.adapter.listener.FilmClickListener;
import mk.ukim.finki.lab2_fragmentsanddialog.data.model.Film;

public class MovieListFragment extends Fragment {

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

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        FilmAdapter filmAdapter = new FilmAdapter(FakeApi.getInstance().getFilms());

        filmAdapter.setFilmClickListener(new FilmClickListener() {
            @Override
            public void onFilmClicked(int position) {
                Bundle bundle = new Bundle();
                bundle.putInt("student_num",position);
                NavHostFragment.findNavController(MovieListFragment.this)
                        .navigate(R.id.action_MovieListFragment_to_MovieDetailsFragment,bundle);
            }
        });

        recyclerView.setAdapter(filmAdapter);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Adding new Film");
        View view = LayoutInflater.from(getContext()).inflate(R.layout.add_film_dialog,null);
        builder.setView(view);
        final EditText filmId,filmName,filmDirector,filmDesc,filmActors;
        filmId = view.findViewById(R.id.etFilmId);
        filmName = view.findViewById(R.id.etFilmName);
        filmDirector = view.findViewById(R.id.etDirector);
        filmDesc = view.findViewById(R.id.etDescription);
        filmActors = view.findViewById(R.id.etActors);


        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(filmId.getText().toString().isEmpty()||
                        filmName.getText().toString().isEmpty()||
                        filmDesc.getText().toString().isEmpty()||
                        filmDirector.getText().toString().isEmpty()||
                        filmActors.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(),"Please fill the fields",Toast.LENGTH_LONG).show();
                    return;
                }

                String[] actors = filmActors.getText().toString().split(",");

                Film film = new Film(Integer.parseInt(filmId.getText().toString()),
                        filmName.getText().toString(),
                        filmDesc.getText().toString(),
                        filmDirector.getText().toString(),
                        Arrays.stream(actors).collect(Collectors.toList()));

                FakeApi.getInstance().addFilm(film);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
}