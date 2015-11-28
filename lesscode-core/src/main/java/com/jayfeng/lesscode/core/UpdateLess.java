package com.jayfeng.lesscode.core;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 检查更新工具类
 */
public class UpdateLess {

    /**
     * {
     * "vercode":1,
     * "vername":"v1.1",
     * "download":"http://www.jayfeng.com/lesscode-app.apk",
     * "log":"upgrade content"
     * }
     *
     * @return whether has a update version
     */
    public static boolean $check(final Context context, String updateJson) {
        int vercode = 0;
        String vername = "";
        String log = "";
        String download;

        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(updateJson);
            vercode = jsonObject.optInt("vercode");
            vername = jsonObject.optString("vername");
            download = jsonObject.optString("download");
            log = jsonObject.optString("log");
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return $check(context, vercode, vername, download, log);
    }

    public static boolean $check(final Context context,
                                 int vercode,
                                 String vername,
                                 final String download,
                                 String log) {

        if (vercode <= AppLess.$vercode()) {
            return false;
        }

        new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.less_app_download_dialog_title) + vername)
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

    public static boolean $hasUpdate(int vercode) {
        if (vercode <= AppLess.$vercode()) {
            return false;
        }
        return true;
    }

    public static void $download(Context context, String download) {
        Intent intent = new Intent(context, UpdateService.class);
        intent.putExtra($.KEY_DOWNLOAD_URL, download);
        context.startService(intent);
    }
}
