package com.udacity.dareed.popularmovies.movies;

import android.os.Parcelable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Movie;
import com.udacity.dareed.popularmovies.models.Response;
import com.udacity.dareed.popularmovies.settings.PreferenceManager;
import com.udacity.dareed.popularmovies.API.TheMovieDB;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;

public class MoviesFragment extends android.app.Fragment implements AdapterView.OnItemClickListener {

    private static final String STATE_GRID = "gridState";

    Parcelable gridState;
    TheMovieDB movieDb;

    PopularMoviesAdapter moviesAdapter;

    @InjectView(R.id.gridPopularMovies)
    GridView gridPopularMovies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        gridPopularMovies.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        movieDb = new TheMovieDB();
        fetchMovies();
    }

    private void fetchMovies() {
        movieDb.getMovieDbService().getPopularMovies(PreferenceManager.getSortType(getActivity()), getPopularMoviesCallback);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);
        gridState = gridPopularMovies.onSaveInstanceState();
        state.putParcelable(STATE_GRID, gridState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            gridState = savedInstanceState.getParcelable(STATE_GRID);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gridPopularMovies.setItemChecked(position, true);
        ((SelectionCallback)getActivity()).onSelectedMovieChanged(moviesAdapter.getItem(position));
    }

    private void loadMoviesIntoGridView(List<Movie> movies) {
        moviesAdapter = new PopularMoviesAdapter(getActivity(), movies, PreferenceManager.isShowingFavoritesOnly(getActivity()));
        gridPopularMovies.setAdapter(moviesAdapter);
        if(gridState != null) {
            gridPopularMovies.onRestoreInstanceState(gridState);
        }
    }

    Callback<Response> getPopularMoviesCallback = new Callback<Response>() {
        @Override
        public void success(Response movies, retrofit.client.Response response) {
            loadMoviesIntoGridView(movies.results);
        }

        @Override
        public void failure(RetrofitError error) {
        }
    };

    public interface SelectionCallback {
        void onSelectedMovieChanged(Movie movie);
    }

}