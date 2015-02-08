package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class ActivityLess {

    public static void $noTitle(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static void $fullScreen(Activity activity) {
        $fullScreen(activity, true);
    }

    public static void $fullScreen(Activity activity, boolean fullScreen) {
        if (fullScreen) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }

    }
}
