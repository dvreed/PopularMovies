package com.udacity.dareed.popularmovies.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.udacity.dareed.popularmovies.R;
import com.udacity.dareed.popularmovies.models.Movie;

import java.util.HashSet;
import java.util.Set;


public class PreferenceManager {
    public static String getSortType(Context context) {
        SharedPreferences prefs = getDefaultSharedPreferences(context);
        String defaultSort = context.getResources().getStringArray(R.array.pref_sort_by_values)[0];
        return prefs.getString(context.getString(R.string.pref_key_sort), defaultSort);
    }

    public static void addMovieToFavorites(Context context, Movie movie) {
        SharedPreferences prefs = getDefaultSharedPreferences(context);
        String keyFavorites = context.getString(R.string.pref_key_favorites);
        Set<String> favorites = prefs.getStringSet(keyFavorites, new HashSet<String>());
        favorites.add(movie.id);
        prefs.edit().putStringSet(keyFavorites, favorites).commit();
    }

    public static boolean isMovieInFavorites(Context context, Movie movie) {
        SharedPreferences prefs = getDefaultSharedPreferences(context);
        String keyFavorites = context.getString(R.string.pref_key_favorites);
        Set<String> favorites = prefs.getStringSet(keyFavorites, new HashSet<String>());
        return favorites.contains(movie.id);
    }

    public static Set<String> getFavoriteMovieIds(Context context) {
        SharedPreferences prefs = getDefaultSharedPreferences(context);
        String keyFavorites = context.getString(R.string.pref_key_favorites);
        return prefs.getStringSet(keyFavorites, new HashSet<String>());
    }

    public static boolean isShowingFavoritesOnly(Context context) {
        SharedPreferences prefs = getDefaultSharedPreferences(context);
        return prefs.getBoolean(context.getString(R.string.pref_key_show_favorites_only), false);
    }

    private static SharedPreferences getDefaultSharedPreferences(Context context) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }
}
