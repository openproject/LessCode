package com.jayfeng.lesscode.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class AppLess {

    public static int $vercode(Context context) {
        int result = 0;
        String packageName = context.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            result = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    public static String $vername(Context context) {
        String result = null;
        String packageName = context.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            result = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    public static String $appname(Context context) {
        String result = null;
        String packageName = context.getPackageName();
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            result = packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean $running(Context context, String packageName) {
        if (packageName == null) {
            packageName = context.getPackageName();
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo rapi : infos){
            if(rapi.processName.equals(packageName))
                return true;
        }
        return false;
    }

    public static boolean isTopActivy(Context context, String activityName) {
        ActivityManager manager = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
        String cmpNameTemp = null;

        if (runningTaskInfos != null) {
            cmpNameTemp = runningTaskInfos.get(0).topActivity.getShortClassName();
        }

        if (cmpNameTemp == null) {
            return false;
        }
        return cmpNameTemp.endsWith(activityName);
    }

}
