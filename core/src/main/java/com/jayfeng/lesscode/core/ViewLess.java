package com.jayfeng.lesscode.core;

import android.app.Activity;
import android.view.View;

public class ViewLess {

    public static <T extends View> T $(Activity activity, int viewId) {
        return (T)activity.findViewById(viewId);
    }

    public static <T extends View> T $(View parent, int viewId) {
        return (T)parent.findViewById(viewId);
    }
}
