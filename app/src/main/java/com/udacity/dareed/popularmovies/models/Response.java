package com.udacity.dareed.popularmovies.models;

import java.util.ArrayList;


public class Response {
    public final int page;
    public final ArrayList<Movie> results;

    public Response(int page, ArrayList<Movie> results) {
        this.page = page;
        this.results = results;
    }
}
