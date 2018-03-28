package com.jayfeng.lesscode.core;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * 手机网络相关的工具类
 */
public final class NetworkLess {

    /**
     * 定义网络类型的枚举分类
     * 这里把一些一些2G,2.5G,2.7G等等按照快慢又做了一个分类,仅供参考
     */
    public static enum NetworkType {
        WIRED_FAST, WIFI_FAST, MOBILE_FAST, MOBILE_MIDDLE, MOBILE_SLOW, NONE,
    }

    /**
     * 是否网络在线
     * @return
     */
    public static boolean $online() {
        ConnectivityManager manager = (ConnectivityManager) $.sAppContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null) {
            return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 当期的网络类型
     * @return
     */
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
                case TelephonyManager.NETWORK_TYPE_GSM:
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
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA:
                    return NetworkType.MOBILE_MIDDLE;// 3G

                case TelephonyManager.NETWORK_TYPE_LTE:
                case TelephonyManager.NETWORK_TYPE_IWLAN:
                case 19: // TelephonyManager.NETWORK_TYPE_LTE_CA
                    return NetworkType.MOBILE_FAST; // 4G
                default: // we think the network will be more and more better
                    return NetworkType.MOBILE_FAST;
            }
        }

        return NetworkType.NONE;
    }

}
