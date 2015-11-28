package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public class ActivityLess {

    /**
     * 第一次和第二次的退出间隔时间基准
     */
    private static final long EXIT_TWICE_INTERVAL = 2000;

    private static long mExitTime = 0;

    /**
     * 设置Activity无标题
     * @param activity
     */
    public static void $noTitle(Activity activity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 设置Activity全屏
     * @param activity
     */
    public static void $fullScreen(Activity activity) {
        $fullScreen(activity, true);
    }

    /**
     * 根据参数设置Activity是否全屏
     * @param activity
     * @param fullScreen
     */
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

    /**
     * 第二次按退出则返回true,否则返回false
     * @return
     */
    public static boolean $exitTwice() {
        long newExitTime = System.currentTimeMillis();
        if (newExitTime - mExitTime > EXIT_TWICE_INTERVAL) {
            mExitTime = newExitTime;
            return false;
        } else {
            return true;
        }
    }
}
