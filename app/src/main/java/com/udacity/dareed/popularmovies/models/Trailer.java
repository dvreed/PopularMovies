package com.udacity.dareed.popularmovies.models;

public class Trailer {

    public final String name;
    public final String site;
    public final String key;

    public Trailer(String name, String type, String key) {
        this.name = name;
        this.site = type;
        this.key = key;
    }

    public static class Tools {
        public static String buildYoutubeUrl(Trailer trailer) {
            return "http://www.youtube.com/watch?v=" + trailer.key;
        }
    }
}