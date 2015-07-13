package com.udacity.dareed.popularmovies.movies;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Movie;
import com.udacity.dareed.popularmovies.movieDetails.MovieDetailsActivity;
import com.udacity.dareed.popularmovies.movieDetails.MovieDetailsFragment;
import com.udacity.dareed.popularmovies.settings.SettingsActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MoviesActivity extends AppCompatActivity implements MoviesFragment.SelectionCallback{

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    private static String TAG_DETAILS_FRAGMENT = "detailsFragment";
    private boolean isInTwoPaneLayoutMode = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.inject(this);
        setSupportActionBar(toolbar);
        if(findViewById(R.id.movie_details_fragment_container) != null) {
            isInTwoPaneLayoutMode = true;
        }
    }

    private void setupMovieDetailsFragment(Movie movie) {
        getFragmentManager().beginTransaction().replace(R.id.movie_details_fragment_container, MovieDetailsFragment.newInstance(movie), TAG_DETAILS_FRAGMENT).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectedMovieChanged(Movie movie) {
        if(isInTwoPaneLayoutMode) {
            setupMovieDetailsFragment(movie);
        } else {
            startActivity(MovieDetailsActivity.buildIntent(this, movie));
        }
    }
}