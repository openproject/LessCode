package com.jayfeng.lesscode.core;

import android.content.Context;
import android.widget.Toast;

public class ToastLess {

    public static void $(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    };

}
