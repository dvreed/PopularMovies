package com.udacity.dareed.popularmovies.API;

import com.udacity.dareed.popularmovies.models.Response;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;


public interface APIService {

    @GET("/discover/movie")
    void getPopularMovies(@Query("sort_by") String sortType, Callback<Response> callback);

    //TODO:  Add reviews and trailer calls here
}
