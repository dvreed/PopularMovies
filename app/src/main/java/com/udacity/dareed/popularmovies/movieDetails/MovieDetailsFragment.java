package com.udacity.dareed.popularmovies.movieDetails;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.udacity.dareed.popularmovies.ImageUtils;
import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.API.TheMovieDB;
import com.udacity.dareed.popularmovies.models.Movie;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MovieDetailsFragment extends Fragment {

    static final String EXTRA_MOVIE = "MOVIE";

    @InjectView(R.id.image)
    ImageView imagePoster;
    @InjectView(R.id.release_date)
    TextView tvReleaseDate;
    @InjectView(R.id.plot) TextView tvPlot;
    @InjectView(R.id.rating) TextView tvRating;

    TheMovieDB movieDb = new TheMovieDB();
    Movie movie;

    public static Fragment newInstance(Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();

        Bundle args = new Bundle();
        args.putParcelable(EXTRA_MOVIE, movie);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_details, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);
        updateUi();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    private Movie getMovie() {
        if(movie == null) {
            //movie = (Movie) getArguments().getSerializable(EXTRA_MOVIE);
            movie = (Movie) getArguments().getParcelable(EXTRA_MOVIE);
        }
        return movie;
    }


    private void updateUi() {
        Picasso.with(getActivity()).load(Movie.Tools.getFullPosterPath(getMovie(), getString(R.string.param_poster_size))).into(imagePoster, new Callback() {
            @Override
            public void onSuccess() {
                updateBackgroundColor();
            }

            @Override
            public void onError() {
            }
        });

        tvPlot.setText(getMovie().overview);
        tvRating.setText(getString(R.string.rating_out_of_ten, String.format("%.1f", getMovie().vote_average)));
        tvReleaseDate.setText(getMovie().getReleaseYear());
    }

    private void updateBackgroundColor() {
        Bitmap bitmap = ImageUtils.getBitmap(imagePoster);
        Palette palette = Palette.from(bitmap).generate();
        int colorStart = palette.getDarkMutedColor(R.color.primary);
        int colorEnd = palette.getDarkVibrantColor(R.color.primaryLight);
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BR_TL, new int[]{colorStart, colorEnd});
        gradientDrawable.setDither(true);
        getActivity().getWindow().setBackgroundDrawable(gradientDrawable);
    }


}
