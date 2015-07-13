package com.udacity.dareed.popularmovies.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class TheMovieDB implements RequestInterceptor {

    private static final String API_KEY = "a36adfb6ceece3c0f388124538dcc644";
    private APIService movieDbService;

    public TheMovieDB() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        GsonConverter gsonConverter = new GsonConverter(gson);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint("http://api.themoviedb.org/3").setConverter(gsonConverter).setRequestInterceptor(this).build();
        movieDbService = restAdapter.create(APIService.class);
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addEncodedQueryParam("api_key", API_KEY);
    }

    public APIService getMovieDbService() {
        return movieDbService;
    }

}
