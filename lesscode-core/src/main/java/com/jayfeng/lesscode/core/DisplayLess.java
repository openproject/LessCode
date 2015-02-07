package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.content.res.Resources;
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

    private static int $dp2px(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }
}
