package com.jayfeng.lesscode.core;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public final class AlarmLess {

    /**
     * 开启定时器
     * @param context
     * @param triggerAtMillis
     * @param pendingIntent
     */
    public static void $startIntent(Context context, int triggerAtMillis, PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.set(AlarmManager.RTC_WAKEUP, triggerAtMillis, pendingIntent);
    }

    /**
     * 停止定时器
     * @param context
     * @param pendingIntent
     */
    public static void $stop(Context context, PendingIntent pendingIntent) {
        AlarmManager manager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
    }

    /**
     * 开启轮询闹钟
     * @param context
     * @param triggerAtMillis
     * @param cls
     * @param action
     */
    public static void $startAction(Context context, int triggerAtMillis, Class<?> cls, String action) {
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        $startIntent(context, triggerAtMillis, pendingIntent);
    }

    /**
     * 停止轮询闹钟
     * @param context
     * @param cls
     * @param action
     */
    public static void $stop(Context context, Class<?> cls, String action) {
        Intent intent = new Intent(context, cls);
        intent.setAction(action);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        $stop(context, pendingIntent);
    }
}
