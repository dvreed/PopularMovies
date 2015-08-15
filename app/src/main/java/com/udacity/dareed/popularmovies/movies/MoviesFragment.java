package com.udacity.dareed.popularmovies.movies;

import android.os.Bundle;
import android.os.Parcelable;
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

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RetrofitError;

public class MoviesFragment extends android.app.Fragment implements AdapterView.OnItemClickListener {

    private static final String STATE_GRIDVIEW = "GridView";
    private static final String STATE_MOVIE_LIST = "MovieList";

    private ArrayList<Movie> mMovieList;
    private Parcelable mGridViewState;

    TheMovieDB mMovieDB;

    PopularMoviesAdapter moviesAdapter;

    @InjectView(R.id.gridPopularMovies)
    GridView mPopularMoviesGridView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null
                || !savedInstanceState.containsKey(STATE_GRIDVIEW)
                || !savedInstanceState.containsKey(STATE_MOVIE_LIST)) {
            mMovieDB = new TheMovieDB();
            fetchMovies();
        }
        else {
            // restore the move list from the saved bundle
            mMovieList = savedInstanceState.getParcelableArrayList(STATE_MOVIE_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        mPopularMoviesGridView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }


    private void fetchMovies() {
        mMovieDB.getMovieDbService().getPopularMovies(PreferenceManager.getSortType(getActivity()), getPopularMoviesCallback);
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        state.putParcelableArrayList(STATE_MOVIE_LIST, mMovieList);
        mGridViewState = mPopularMoviesGridView.onSaveInstanceState();
        state.putParcelable(STATE_GRIDVIEW, mGridViewState);
        super.onSaveInstanceState(state);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            // Populate the gridview with our movies on restore (like on screen rotation)
            loadMoviesIntoGridView(mMovieList);
            // Restore our scrolling position
            mGridViewState = savedInstanceState.getParcelable(STATE_GRIDVIEW);
            mPopularMoviesGridView.onRestoreInstanceState(mGridViewState);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mPopularMoviesGridView.setItemChecked(position, true);
        ((SelectionCallback)getActivity()).onSelectedMovieChanged(moviesAdapter.getItem(position));
    }

    private void loadMoviesIntoGridView(ArrayList<Movie> movies) {
        moviesAdapter = new PopularMoviesAdapter(getActivity(), movies, PreferenceManager.isShowingFavoritesOnly(getActivity()));
        mPopularMoviesGridView.setAdapter(moviesAdapter);
    }

    Callback<Response> getPopularMoviesCallback = new Callback<Response>() {
        @Override
        public void success(Response movies, retrofit.client.Response response) {
            mMovieList = movies.results;
            loadMoviesIntoGridView(mMovieList);
        }

        @Override
        public void failure(RetrofitError error) {
        }
    };

    public interface SelectionCallback {
        void onSelectedMovieChanged(Movie movie);
    }

}