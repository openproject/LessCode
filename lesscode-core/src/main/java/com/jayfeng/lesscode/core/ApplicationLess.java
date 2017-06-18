package com.jayfeng.lesscode.core;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;

import java.util.List;

public final class ApplicationLess {

    /**
     * 根据进程名称判断第一次真正的初始化
     *
     * @param context
     * @return
     */
    public static boolean $init(Context context) {
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
        String mainProcessName = context.getPackageName();
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo info : processInfos) {
            if (info.pid == myPid && mainProcessName.equals(info.processName)) {
                return true;
            }
        }
        return false;
    }
}
