package com.jayfeng.lesscode.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class NetworkLess {

    public static enum NetworkType {
        WIRED_FAST, WIFI_FAST, MOBILE_FAST, MOBILE_MIDDLE, MOBILE_SLOW, NONE,
    }

    public static boolean $online() {
        NetworkType networkType = $type();
        return networkType != NetworkType.NONE;
    }

    public static NetworkType $type() {
        ConnectivityManager manager = (ConnectivityManager) $.sAppContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();

        if (info == null || !info.isConnected()) {
            return NetworkType.NONE;
        }

        int type = info.getType();
        int subType = info.getSubtype();


        if (type == ConnectivityManager.TYPE_ETHERNET) {
            return NetworkType.WIRED_FAST;
        }

        if (type == ConnectivityManager.TYPE_WIFI) {
            return NetworkType.WIFI_FAST;
        }

        if (type == ConnectivityManager.TYPE_MOBILE) {
            switch (subType) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NetworkType.MOBILE_SLOW; // 2G

                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NetworkType.MOBILE_MIDDLE;// 3G

                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NetworkType.MOBILE_FAST; // 4G
            }
        }

        return NetworkType.NONE;
    }

}
