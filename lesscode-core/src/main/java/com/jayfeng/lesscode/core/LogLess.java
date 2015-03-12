package com.jayfeng.lesscode.core;

import android.text.TextUtils;
import android.util.Log;

public class LogLess {

    public static void v(String str) {
        if ($.sDebug) {
            Log.v(getTag(), buildLogString(str));
        }
    }

    public static void $d(String str) {
        if ($.sDebug) {
            Log.d(getTag(), buildLogString(str));
        }
    }

    public static void $i(String str) {
        if ($.sDebug) {
            Log.i(getTag(), buildLogString(str));
        }
    }

    public static void $w(String str) {
        if ($.sDebug) {
            Log.w(getTag(), buildLogString(str));
        }
    }

    public static void $e(String str) {
        if ($.sDebug) {
            Log.e(getTag(), buildLogString(str));
        }
    }

    private static String getTag() {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        if (TextUtils.isEmpty($.sTAG)) {
            return caller.getFileName();
        }
        return $.sTAG;
    }

    private static String buildLogString(String str) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        StringBuilder stringBuilder = new StringBuilder();
        if (TextUtils.isEmpty($.sTAG)) {
            stringBuilder.append(caller.getMethodName())
                    .append("():")
                    .append(caller.getLineNumber())
                    .append(":")
                    .append(str);
        } else {
            stringBuilder
                    .append(caller.getFileName())
                    .append(".")
                    .append(caller.getMethodName())
                    .append("():")
                    .append(caller.getLineNumber())
                    .append(":")
                    .append(str);
        }
        return stringBuilder.toString();
    }
}
