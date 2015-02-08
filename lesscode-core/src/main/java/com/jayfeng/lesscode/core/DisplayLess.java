package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

public class DisplayLess {

    public static int $width(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }


    public static int $height(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int $dp2px(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static boolean $tablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
