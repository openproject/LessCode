package com.jayfeng.lesscode.core;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

public class DeviceLess {

    public static String $mac() {
        WifiManager wifi = (WifiManager) $.sAppContext.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }
}
