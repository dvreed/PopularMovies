package com.udacity.dareed.popularmovies.models;

import java.util.List;

public class TrailersResponse {
    public final List<Trailer> results;

    public TrailersResponse(List<Trailer> results) {
        this.results = results;
    }
}