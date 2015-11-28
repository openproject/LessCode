package com.jayfeng.lesscode.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

public class AppLess {

    /**
     * 获取app的版本数versionCode,比如38
     * @return
     */
    public static int $vercode() {
        int result = 0;
        String packageName = $.sAppContext.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = $.sAppContext.getPackageManager().getPackageInfo(packageName, 0);
            result = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    /**
     * 获取app的版本名versionName,比如0.6.9
     * @return
     */
    public static String $vername() {
        String result = null;
        String packageName = $.sAppContext.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = $.sAppContext.getPackageManager().getPackageInfo(packageName, 0);
            result = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    /**
     * 获取app的名称
     * @return
     */
    public static String $appname() {
        String result = null;
        String packageName = $.sAppContext.getPackageName();
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = $.sAppContext.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            result = packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断一个app是否在运行
     * @param packageName app的包名
     * @return 在运行则返回true,否则false
     */
    public static boolean $running(String packageName) {
        if (packageName == null) {
            packageName = $.sAppContext.getPackageName();
        }
        ActivityManager am = (ActivityManager) $.sAppContext.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> infos = am.getRunningAppProcesses();
        for(ActivityManager.RunningAppProcessInfo rapi : infos){
            if(rapi.processName.equals(packageName))
                return true;
        }
        return false;
    }

    /**
     * 判断一个activity是否在前台运行
     * @param activityName activity的全路径名称
     * @return 在前台则返回true,否则返回false
     */
    public static boolean isTopActivy(String activityName) {
        ActivityManager manager = (ActivityManager) $.sAppContext.getSystemService(Context.ACTIVITY_SERVICE);
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
