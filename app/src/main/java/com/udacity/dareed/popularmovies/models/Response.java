package com.udacity.dareed.popularmovies.models;

import java.util.List;


public class Response {
    public final int page;
    public final List<Movie> results;

    public Response(int page, List<Movie> results) {
        this.page = page;
        this.results = results;
    }
}
