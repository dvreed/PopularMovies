package com.udacity.dareed.popularmovies;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

public class ImageUtils {

    public static Bitmap getBitmap(ImageView imageView) {
        return  ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

}
