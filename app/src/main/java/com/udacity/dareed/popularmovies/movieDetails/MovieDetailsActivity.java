package com.udacity.dareed.popularmovies.movieDetails;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Movie;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MovieDetailsActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    static final String EXTRA_MOVIE = "MOVIE";
    Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.inject(this);
        loadFromIntent(getIntent());
        if(movie == null) {
            finish();
            return;
        }
        setupToolbar();
        setupFragment(savedInstanceState);
    }

    private void setupFragment(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            return;
        }
        getFragmentManager().beginTransaction().add(R.id.fragment_container, MovieDetailsFragment.newInstance(movie)).commit();
    }

    private void setupToolbar() {
        toolbar.setTitle(movie.original_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public static Intent buildIntent(Activity activity, Movie movie) {
        Intent intent = new Intent(activity, MovieDetailsActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    public void loadFromIntent(Intent intent) {
        if(intent == null) {
            finish();
            return;
        }
        //movie = (Movie) intent.getSerializableExtra(EXTRA_MOVIE);
        movie = (Movie) intent.getParcelableExtra(EXTRA_MOVIE);
    }

}