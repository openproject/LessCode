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
    static String sUpdateJsonUrl;
    public $ app(String updateJsonUrl) {
        sUpdateJsonUrl = updateJsonUrl;
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
