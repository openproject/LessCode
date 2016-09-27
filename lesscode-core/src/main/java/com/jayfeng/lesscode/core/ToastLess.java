package com.jayfeng.lesscode.core;

import android.content.Context;
import android.widget.Toast;

/**
 * 简化Toast的工具类
 */
public final class ToastLess {

    @Deprecated
    public static void $(Context context, String message) {
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public static void $(Context context, int stringId) {
        Toast.makeText(context.getApplicationContext(), stringId, Toast.LENGTH_SHORT).show();
    }

    public static void $(String message) {
        Toast.makeText($.sAppContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void $(int stringId) {
        Toast.makeText($.sAppContext, stringId, Toast.LENGTH_SHORT).show();
    }
}
