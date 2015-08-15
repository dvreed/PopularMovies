package com.udacity.dareed.popularmovies.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Parcelable {

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

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getReleaseYear() {
        return releaseYearDateFormat.format(release_date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(original_title);
        dest.writeString(overview);
        dest.writeString(poster_path);
        dest.writeFloat(vote_average);
        dest.writeString(release_date.toString());
    }

    private Movie (Parcel in) {
        id = in.readString();
        original_title = in.readString();
        overview = in.readString();
        poster_path = in.readString();
        vote_average = in.readFloat();
        release_date = new Date(in.readString());
    }

    public static class Tools {
        public static String getFullPosterPath(Movie movie, String posterSize) {
            return "http://image.tmdb.org/t/p/" + posterSize + "/" + movie.poster_path;
        }
    }

}
