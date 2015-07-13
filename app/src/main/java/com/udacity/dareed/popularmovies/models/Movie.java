package com.udacity.dareed.popularmovies.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Serializable {

    private static SimpleDateFormat releaseYearDateFormat = new SimpleDateFormat("yyyy");

    public final String id;
    public final String original_title;
    public final String overview;
    public final String poster_path;
    public final float vote_average;
    public final Date release_date;

    public Movie(String id, String original_title, String overview, String poster_path, float vote_average, Date release_date) {
        this.id = id;
        this.original_title = original_title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.release_date = release_date;
    }

    public String getReleaseYear() {
        return releaseYearDateFormat.format(release_date);
    }

    public static class Tools {
        public static String getFullPosterPath(Movie movie, String posterSize) {
            return "http://image.tmdb.org/t/p/" + posterSize + "/" + movie.poster_path;
        }
    }

}
