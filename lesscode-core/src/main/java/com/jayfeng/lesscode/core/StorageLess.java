package com.jayfeng.lesscode.core;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 手机存储相关的工具类
 * 包括:手机内存,内置存储卡(Sdcard),外置存储卡(ExtSdcard)
 */
public class StorageLess {

    public static class Phone {
        public static long $total() {
            File path = Environment.getDataDirectory();
            return total(path);
        }

        public static long $used() {
            File path = Environment.getDataDirectory();
            return used(path);
        }

        public static long $free() {
            File path = Environment.getDataDirectory();
            return free(path);
        }

        public static String $totalString(Context context) {
            return Formatter.formatFileSize(context, $total());
        }
        public static String $usedString(Context context) {
            return Formatter.formatFileSize(context, $used());
        }
        public static String $freeString(Context context) {
            return Formatter.formatFileSize(context, $free());
        }

    }

    public static class Sdcard {

        public static boolean $ready() {
            String state = Environment.getExternalStorageState();
            return Environment.MEDIA_MOUNTED.equals(state);
        }

        public static File $path() {
            return Environment.getExternalStorageDirectory();
        }

        public static long $total() {
            File path = Environment.getExternalStorageDirectory();
            return total(path);
        }

        public static long $used() {
            File path = Environment.getExternalStorageDirectory();
            return used(path);
        }

        public static long $free() {
            File path = Environment.getExternalStorageDirectory();
            return free(path);
        }

        public static String $totalString(Context context) {
            return Formatter.formatFileSize(context, $total());
        }
        public static String $usedString(Context context) {
            return Formatter.formatFileSize(context, $used());
        }
        public static String $freeString(Context context) {
            return Formatter.formatFileSize(context, $free());
        }
    }

    public static class ExtSdcard {

        public static File $path() {
            List<String> paths = new ArrayList<String>();
            String extFileStatus = Environment.getExternalStorageState();
            File extFile = Environment.getExternalStorageDirectory();
            if (extFileStatus.equals(Environment.MEDIA_MOUNTED)
                    && extFile.exists() && extFile.isDirectory()
                    && extFile.canWrite()) {
                paths.add(extFile.getAbsolutePath());
            }
            try {
                // obtain executed result of command line code of 'mount', to judge
                // whether tfCard exists by the result
                Runtime runtime = Runtime.getRuntime();
                Process process = runtime.exec("mount");
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String line = null;
                int mountPathIndex = 1;
                while ((line = br.readLine()) != null) {
                    // format of sdcard file system: vfat/fuse
                    if ((!line.contains("fat") && !line.contains("fuse") && !line
                            .contains("storage"))
                            || line.contains("secure")
                            || line.contains("asec")
                            || line.contains("firmware")
                            || line.contains("shell")
                            || line.contains("obb")
                            || line.contains("legacy") || line.contains("data")) {
                        continue;
                    }
                    String[] parts = line.split(" ");
                    int length = parts.length;
                    if (mountPathIndex >= length) {
                        continue;
                    }
                    String mountPath = parts[mountPathIndex];
                    if (!mountPath.contains("/") || mountPath.contains("data")
                            || mountPath.contains("Data")) {
                        continue;
                    }
                    File mountRoot = new File(mountPath);
                    if (!mountRoot.exists() || !mountRoot.isDirectory()
                            || !mountRoot.canWrite()) {
                        continue;
                    }
                    boolean equalsToPrimarySD = mountPath.equals(extFile
                            .getAbsolutePath());
                    if (equalsToPrimarySD) {
                        continue;
                    }
                    paths.add(mountPath);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (paths.size() > 1) {
                return new File(paths.get(1));
            }
            return null;
        }

        public static long $total() {
            File path = Environment.getExternalStorageDirectory();
            return total(path);
        }

        public static long $used() {
            File path = Environment.getExternalStorageDirectory();
            return used(path);
        }

        public static long $free() {
            File path = Environment.getExternalStorageDirectory();
            return free(path);
        }

        public static String $totalString(Context context) {
            return Formatter.formatFileSize(context, $total());
        }
        public static String $usedString(Context context) {
            return Formatter.formatFileSize(context, $used());
        }
        public static String $freeString(Context context) {
            return Formatter.formatFileSize(context, $free());
        }
    }

    private static long total(File path) {
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return blockSize * totalBlocks;
    }

    private static long free(File path) {
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long freeBlocks = stat.getAvailableBlocks();
        return blockSize * freeBlocks;
    }

    private static long used(File path) {
        return total(path) - free(path);
    }
}
