package com.jayfeng.lesscode.core;

import java.io.File;
import java.io.IOException;

/**
 * 缓存相关的工具类
 * 常用于缓存网络请求返回的json保存到本地的缓存策略
 */
public class CacheLess {

    /**
     * 根据key查找缓存的内容
     * @param key
     * @param expireTime
     * @return
     */
    public static String $get(String key, long expireTime) {
        String result = null;

        File cacheDir = $.sAppContext.getCacheDir();
        if (StorageLess.Sdcard.$ready()) {
            cacheDir = $.sAppContext.getExternalCacheDir();
        }

        File file = new File(cacheDir, EncodeLess.$md5(key));
        if (file.exists() && file.isFile()) {
            long storageTime = System.currentTimeMillis() - file.lastModified();

            boolean online = NetworkLess.$online();

            // 如果系统时间不对(被调回很久之前),storageTime和expireTime的比较就没有意义了
            // 在这种情况下,如果有网就请求新内容,否则才考虑缓存
            if (online && storageTime < 0) {
                return null;
            }
            // 如果有网的情况下,且缓存已经过期,则返回null
            // 在无网的情况下, 即使过期也返回缓存
            if (online && storageTime > expireTime) {
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

    /**
     * 设置字符串为特定key的缓存,保存到本地
     * @param key
     * @param data
     */
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
