package com.jayfeng.lesscode.core;

import android.util.Log;

public class LogLess {

    public static void v(String str) {
        if ($.sDebug) {
            Log.v($.sTAG, str);
        }
    }

    public static void $d(String str) {
        if ($.sDebug) {
            Log.d($.sTAG, str);
        }
    }

    public static void $i(String str) {
        if ($.sDebug) {
            Log.i($.sTAG, str);
        }
    }

    public static void $w(String str) {
        if ($.sDebug) {
            Log.w($.sTAG, str);
        }
    }

    public static void $e(String str) {
        if ($.sDebug) {
            Log.e($.sTAG, str);
        }
    }
}
