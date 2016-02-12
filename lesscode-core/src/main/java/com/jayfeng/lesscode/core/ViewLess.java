package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

import java.util.Calendar;

/**
 * View相关工具类
 */
public final class ViewLess {

    /**
     * ***********************************************************
     * findViewById的一种更优雅的写法
     * 原理:泛型的类型推断
     * ***********************************************************
     */
    public static <T extends View> T $(Activity activity, int viewId) {
        return (T) activity.findViewById(viewId);
    }

    public static <T extends View> T $(View view, int viewId) {
        return (T) view.findViewById(viewId);
    }

    public static <T extends View> T $(Dialog dialog, int viewId) {
        return (T) dialog.findViewById(viewId);
    }
}
