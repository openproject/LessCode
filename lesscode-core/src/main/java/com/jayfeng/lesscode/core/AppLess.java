package com.jayfeng.lesscode.core;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.net.URLEncoder;

public class AppLess {

    public static int $vercode(Context context) {
        int result = 0;
        String packageName = context.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            result = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    public static String $vername(Context context) {
        String result = null;
        String packageName = context.getPackageName();
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            result = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        }
        return result;
    }

    public static String $appname(Context context) {
        String result = null;
        String packageName = context.getPackageName();
        ApplicationInfo applicationInfo;
        try {
            PackageManager packageManager = context.getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            result = packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            throw new AssertionError(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * {
     * "vercode":1,
     * "vername":"v1.1",
     * "download":"http://www.jayfeng.com/lesscode-app.apk",
     * "log":"upgrade content"
     * }
     *
     * @return
     */
    public static boolean $update(final Context context, String updateJson) {

        int vercode = 0;
        String vername = "";
        String log = "";

        final String download;

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(updateJson);
            vercode = jsonObject.optInt("vercode");
            if (vercode <= $vercode(context)) {
                return false;
            }

            vername = jsonObject.optString("vername");
            download = jsonObject.optString("download");
            log = jsonObject.optString("log");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        new AlertDialog.Builder(context)
                .setTitle("发现新版本:" + vername)
                .setMessage(log)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, UpdateService.class);
                        intent.putExtra($.KEY_DOWNLOAD_URL, download);
                        context.startService(intent);
                    }
                }).show();

        return true;
    }

}
