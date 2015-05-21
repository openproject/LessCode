package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;

public class ViewLess {

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
