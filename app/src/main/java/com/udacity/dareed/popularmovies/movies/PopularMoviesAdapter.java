package com.udacity.dareed.popularmovies.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Movie;
import com.udacity.dareed.popularmovies.settings.PreferenceManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PopularMoviesAdapter extends BaseAdapter {

    private ArrayList<Movie> movies;
    private LayoutInflater layoutInflater;
    private Context context;

    public PopularMoviesAdapter(Context context, ArrayList<Movie> movies, boolean isShowingFavoritesOnly) {
        if(isShowingFavoritesOnly) {
            this.movies = filterFavorites(context, movies);
        } else {
            this.movies = movies;
        }
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    private ArrayList<Movie> filterFavorites(Context context, List<Movie> movies) {
        Set<String> favoriteIds = PreferenceManager.getFavoriteMovieIds(context);
        ArrayList<Movie> favoriteMovies = new ArrayList<>();
        for(Movie movie : movies) {
            if(favoriteIds.contains(movie.id)) {
                favoriteMovies.add(movie);
            }
        }
        return favoriteMovies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_movie, null, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        Picasso.with(context).load(Movie.Tools.getFullPosterPath(movie, context.getString(R.string.param_poster_size))).placeholder(R.drawable.bg_placeholder).into(viewHolder.imageView);
        return convertView;
    }

    static class ViewHolder {
        @InjectView(R.id.image)
        ImageView imageView;

        ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}
