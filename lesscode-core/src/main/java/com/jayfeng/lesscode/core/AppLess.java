package com.jayfeng.lesscode.core;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import java.io.File;
import java.util.List;

public final class AppLess {

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

    /**
     * 获取应用公钥签名
     * @param context
     * @return
     */
    public static Signature $sign(Context context) {
        PackageInfo pi;
        Signature sign = null;
        try {
            pi = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            sign = pi.signatures[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sign;
    }

    /**
     * 比较当前签名HashCode和预设的HashCode
     * @param context
     * @param presetHashCode
     * @return
     */
    public static boolean $signCheckWithHashCode(Context context, int presetHashCode) {
        Signature signature = $sign(context);
        return signature.hashCode() == presetHashCode;
    }

    /**
     * 删除应用数据： cache, file, share prefs, databases
     * @param context
     */
    public static void $clear(Context context) {
        $clearCache(context);
        $clearFiles(context);
        $clearSharedPreference(context);
        $clearDatabase(context);
    }

    /**
     * 删除应用缓存目录
     * @param context
     */
    public static void $clearCache(Context context) {
        FileLess.$del(context.getCacheDir(), true);
        FileLess.$del(context.getExternalCacheDir(), true);
    }

    /**
     * 删除应用文件目录
     * @param context
     */
    public static void $clearFiles(Context context) {
        FileLess.$del(context.getFilesDir(), true);
    }

    /**
     * 删除应用Shared Prefrence目录
     * @param context
     */
    public static void $clearSharedPreference(Context context) {
        FileLess.$del(new File("/data/data/" + context.getPackageName() + "/shared_prefs"), true);
    }

    /**
     * 删除应用数据库目录
     * @param context
     */
    public static void $clearDatabase(Context context) {
        FileLess.$del(new File("/data/data/" + context.getPackageName() + "/databases"), true);
    }
}
