package com.jayfeng.lesscode.core;

import android.os.Environment;

public class StorageLess {

    public static boolean $sdcard() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
