package com.jayfeng.lesscode.core;

public class $ {

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
    public $ update(String downloadSDPath, int notificationFrequent) {
        sNotificationFrequent = notificationFrequent;
        sDownloadSDPath = downloadSDPath;
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
