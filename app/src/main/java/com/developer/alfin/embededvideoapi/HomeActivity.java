package com.developer.alfin.embededvideoapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.developer.alfin.embededvideoapi.adapter.MoviesAdapter;
import com.developer.alfin.embededvideoapi.model.Movies;
import com.developer.alfin.embededvideoapi.viewmodel.MovieViewModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView listViewMovie;
    private MoviesAdapter moviesAdapter;
    private MovieViewModel viewModel;
    public static LottieAnimationView noInternet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        noInternet = findViewById(R.id.no_internet);

        if (isOnline()) {

            moviesAdapter = new MoviesAdapter();
            listViewMovie = findViewById(R.id.test_embed);
            listViewMovie.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
            listViewMovie.setAdapter(moviesAdapter);

            progressBar = findViewById(R.id.progress_bar);

            viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
            viewModel.getMoviesData().observe(this, getMoviesData);
            viewModel.setListMutableLiveData("MOVIE_EXTRA");

            showProgressBar(true);

        } else {

            noInternet.setVisibility(View.VISIBLE);
        }



    }

    private Observer<ArrayList<Movies>> getMoviesData = new Observer<ArrayList<Movies>>() {
        @Override
        public void onChanged(ArrayList<Movies> movies) {
            if (movies != null) {
                moviesAdapter.setDataMovies(movies);
            }
            showProgressBar(false);
        }
    };

    public void showProgressBar(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()) {
            Toast.makeText(getApplicationContext(), "No Internet connection!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
