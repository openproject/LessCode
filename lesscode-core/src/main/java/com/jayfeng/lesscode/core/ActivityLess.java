package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class ActivityLess {

    public static void $noTitle(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    public static void $fullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
