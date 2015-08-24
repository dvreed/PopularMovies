package com.udacity.dareed.popularmovies.models;

import java.util.List;

public class ReviewsResponse {
    public final List<Review> results;

    public ReviewsResponse(List<Review> results) {
        this.results = results;
    }
}
