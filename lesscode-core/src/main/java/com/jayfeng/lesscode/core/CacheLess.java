package com.jayfeng.lesscode.core;

import java.io.File;
import java.io.IOException;

public class CacheLess {

    public static String $get(String key, long expireTime) {
        String result = null;

        File cacheDir = $.sAppContext.getCacheDir();
        if (StorageLess.Sdcard.$ready()) {
            cacheDir = $.sAppContext.getExternalCacheDir();
        }

        File file = new File(cacheDir, EncodeLess.$md5(key));
        if (file.exists() && file.isFile()) {
            long storageTime = System.currentTimeMillis() - file.lastModified();

            NetworkLess.NetworkType networkType = NetworkLess.$type();

            //1. in case the system time is incorrect (the time is turn back long ago)
            //2. when the network is invalid, you can only read the cache
            if (networkType != NetworkLess.NetworkType.NONE && storageTime < 0) {
                return null;
            }
            if(networkType == NetworkLess.NetworkType.WIFI_FAST && storageTime > expireTime) {
                return null;
            } else if ((networkType == NetworkLess.NetworkType.MOBILE_FAST
                    || networkType == NetworkLess.NetworkType.MOBILE_MIDDLE
                    || networkType == NetworkLess.NetworkType.MOBILE_SLOW)
                    && storageTime > expireTime) {
                return null;
            }
            try {
                result = FileLess.$read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void $set(String key, String data) {
        File cacheDir = $.sAppContext.getCacheDir();
        if (StorageLess.Sdcard.$ready()) {
            cacheDir = $.sAppContext.getExternalCacheDir();
        }
        if (!cacheDir.exists()) {
            cacheDir.mkdirs();
        }
        File file = new File(cacheDir, EncodeLess.$md5(key));
        try {
            FileLess.$write(file, data);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
