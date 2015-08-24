package com.udacity.dareed.popularmovies.API;

import com.udacity.dareed.popularmovies.models.Response;
import com.udacity.dareed.popularmovies.models.ReviewsResponse;
import com.udacity.dareed.popularmovies.models.TrailersResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;


public interface APIService {

    @GET("/discover/movie")
    void getPopularMovies(@Query("sort_by") String sortType, Callback<Response> callback);

    @GET("/movie/{id}/videos")
    void getTrailers(@Path("id") String movieId, Callback<TrailersResponse> callback);

    @GET("/movie/{id}/reviews")
    void getReviews(@Path("id") String movieId, Callback<ReviewsResponse> callback);
}
