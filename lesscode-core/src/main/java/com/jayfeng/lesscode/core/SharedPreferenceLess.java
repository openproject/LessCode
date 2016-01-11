package com.jayfeng.lesscode.core;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SharedPreferenceLess {

    public static final String SHARED_NAME = "shared_name";

    public static <T> void $put(Context context, String key, T value) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (value == null) {
            // if value is null, just handler it as a String
            editor.putString(key, null);
        } else {
            if (value.getClass() == Boolean.class) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value.getClass() == Float.class) {
                editor.putFloat(key, (Float) value);
            } else if (value.getClass() == Integer.class) {
                editor.putInt(key, (Integer) value);
            } else if (value.getClass() == Long.class) {
                editor.putLong(key, (Long) value);
            } else if (value.getClass() == String.class) {
                editor.putString(key, (String) value);
            } else {
                throw new RuntimeException("the put value type can't support.");
            }
        }

        SharedPreferencesCompat.apply(editor);
    }

    public static String $get(Context context, String key, String defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        return sp.getString(key, defaultValue);
    }

    public static boolean $get(Context context, String key, boolean defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        return sp.getBoolean(key, defaultValue);
    }

    public static float $get(Context context, String key, float defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        return sp.getFloat(key, defaultValue);
    }

    public static int $get(Context context, String key, int defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        return sp.getInt(key, defaultValue);
    }

    public static long $get(Context context, String key, long defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        return sp.getLong(key, defaultValue);
    }

    public static void $remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void $clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }


    /**
     * *********************************************************************************************
     * Unlike commit(), which writes its preferences out to persistent storage synchronously,
     * apply() commits its changes to the in-memory SharedPreferencesimmediately
     * but starts an asynchronous commit to disk and you won't be notified of any failures.
     * If another editor on this SharedPreferences does a regularcommit() while a apply() is still outstanding,
     * the commit() will block until all async commits are completed as well as the commit itself.
     * *********************************************************************************************
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * check apply mthod by reflect
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * if it has apply(), use apply() first;
         * else just use the commit().
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }
}
