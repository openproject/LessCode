package com.jayfeng.lesscode.core;

import android.util.Log;

public class LogLess {

    private static boolean sDEBUG = true;
    private static String sTAG = null;

    public static void $release() {
        sDEBUG = false;
    }

    public static void $tag(String tag) {
        sTAG = tag;
    }


    public static void v(String str) {
        if (sDEBUG) {
            Log.v(sTAG, str);
        }
    }

    public static void $d(String str) {
        if (sDEBUG) {
            Log.d(sTAG, str);
        }
    }

    public static void $i(String str) {
        if (sDEBUG) {
            Log.i(sTAG, str);
        }
    }

    public static void $w(String str) {
        if (sDEBUG) {
            Log.w(sTAG, str);
        }
    }

    public static void $e(String str) {
        if (sDEBUG) {
            Log.e(sTAG, str);
        }
    }
}
