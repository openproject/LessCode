package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;

/**
 * 屏幕显示相关的工具类
 */
public class DisplayLess {

    /**
     * 屏幕宽度
     * @param activity
     * @return
     */
    public static int $width(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 屏幕高度
     * @param activity
     * @return
     */
    public static int $height(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * dp转px
     * @param dp
     * @return
     */
    public static int $dp2px(float dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 是否为平板
     * @param context
     * @return
     */
    public static boolean $tablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * ****************************
     * 各大设备密度判断
     * ****************************
     */
    public static boolean isLdpi() {
        return $.sAppContext.getResources().getBoolean(R.bool.less_ldpi);
    }
    public static boolean isMdpi() {
        return $.sAppContext.getResources().getBoolean(R.bool.less_mdpi);
    }
    public static boolean isTVdpi() {
        return $.sAppContext.getResources().getBoolean(R.bool.less_tvdpi);
    }
    public static boolean isHdpi() {
        return $.sAppContext.getResources().getBoolean(R.bool.less_hdpi);
    }
    public static boolean isXHdpi() {
        return $.sAppContext.getResources().getBoolean(R.bool.less_xhdpi);
    }
    public static boolean isXXHdpi() {
        return $.sAppContext.getResources().getBoolean(R.bool.less_xxhdpi);
    }
}
