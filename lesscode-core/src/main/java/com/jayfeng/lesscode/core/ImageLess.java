package com.jayfeng.lesscode.core;

import android.media.ExifInterface;

import java.io.IOException;

public final class ImageLess {

    /**
     * 获取图片的exif的旋转角度
     * @param path
     * @return
     */
    public static int $exifRotateAngle(String path) {
        int angle = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    angle = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    angle = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    angle = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return angle;
    }
}
