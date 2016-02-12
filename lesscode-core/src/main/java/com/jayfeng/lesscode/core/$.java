package com.jayfeng.lesscode.core;

import android.content.Context;

public final class $ {
    /**
     * 单例
     */
    private static $ instance;
    public static $ getInstance() {
        if (instance == null) {
            synchronized ($.class) {
                if (instance == null) {
                    instance = new $();
                }
            }
        }
        return instance;
    }

    /**
     * 名义上为build,实则是检查一些必须配置的变量
     */
    public void build(){
        if (sAppContext == null) {
            throw new RuntimeException("please config the lesscode application context");
        }
    }

    /**
     * *********************************************************************************************
     * Global ApplicationContext
     * *********************************************************************************************
     */
    static Context sAppContext;
    public $ context(Context context) {
        sAppContext = context;
        return this;
    }

    /**
     * *********************************************************************************************
     * AppLess
     * *********************************************************************************************
     */
    public static final String KEY_DOWNLOAD_URL = "download_url";
    static String sUpdateJsonUrl;
    public $ app(String updateJsonUrl) {
        sUpdateJsonUrl = updateJsonUrl;
        return this;
    }

    /**
     * *********************************************************************************************
     * UpdateLess
     * *********************************************************************************************
     */
    static int sNotificationFrequent = 5;
    static String sDownloadSDPath;
    static int sUpdateIcon;
    @Deprecated
    public $ update(String downloadSDPath, int notificationFrequent) {
        sNotificationFrequent = notificationFrequent;
        sDownloadSDPath = downloadSDPath;
        return this;
    }

    public $ update(String downloadSDPath, int notificationFrequent, int updateIcon) {
        sNotificationFrequent = notificationFrequent;
        sDownloadSDPath = downloadSDPath;
        sUpdateIcon = updateIcon;
        return this;
    }

    /**
     * *********************************************************************************************
     * HttpLess
     * *********************************************************************************************
     */
    static int sConnectTimeOut = 5000;
    static int sReadTimeout = 5000;
    public $ http(int connectTimeOut, int readTimeout) {
        sConnectTimeOut = connectTimeOut;
        sReadTimeout = readTimeout;
        return this;
    }

    /**
     * *********************************************************************************************
     * LogLess
     * *********************************************************************************************
     */
    static boolean sDebug;
    static String sTAG;
    public $ log(boolean debug, String tag) {
        sDebug = debug;
        sTAG = tag;
        return this;
    }
}
