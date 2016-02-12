package com.jayfeng.lesscode.core;

import android.graphics.BitmapFactory;

public final class BitmapLess {

    /**
     * 根据reqWidth, reqHeight计算最合适的inSampleSize
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int $inSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // raw height and width of image
        int rawWidth = options.outWidth;
        int rawHeight = options.outHeight;

        // calculate best sample size
        int inSampleSize = 0;
        if (rawHeight > reqHeight || rawWidth > reqWidth) {
            float ratioWidth = (float) rawWidth / reqWidth;
            float ratioHeight = (float) rawHeight / reqHeight;
            inSampleSize = (int) Math.min(ratioHeight, ratioWidth);
        }
        inSampleSize = Math.max(1, inSampleSize);

        return inSampleSize;
    }
}
