package com.jayfeng.lesscode.full;

import android.view.View;
import android.view.ViewGroup;

public class ViewLess extends com.jayfeng.lesscode.core.ViewLess {

    public static int $width(View view) {
        if (view == null) {
            return -1;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        return params.width;
    }

    public static void $width(View view, int width) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
    }


    public static int $height(View view) {
        if (view == null) {
            return -1;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        return params.height;
    }

    public static void $height(View view, int height) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }
}
