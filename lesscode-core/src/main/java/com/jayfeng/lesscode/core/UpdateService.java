package com.jayfeng.lesscode.core;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.File;
import java.net.URLEncoder;

/**
 * 检查更新后台下载服务
 */
public class UpdateService extends Service {

    private static final int DOWNLOAD_STATE_FAILURE = -1;
    private static final int DOWNLOAD_STATE_SUCCESS = 0;
    private static final int DOWNLOAD_STATE_START = 1;
    private static final int DOWNLOAD_STATE_INSTALL = 2;
    private static final int DOWNLOAD_STATE_ERROR_SDCARD = 3;
    private static final int DOWNLOAD_STATE_ERROR_URL = 4;

    private static final int NOTIFICATION_ID = 3956;
    private NotificationManager mNotificationManager = null;
    private Notification mNotification = null;
    private PendingIntent mPendingIntent = null;

    private String mDownloadSDPath;
    private String mDownloadUrl;
    private File mDestDir;
    private File mDestFile;

    private Handler.Callback mHandlerCallBack = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD_STATE_SUCCESS:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_success, Toast.LENGTH_LONG).show();
                    install(mDestFile);
                    break;
                case DOWNLOAD_STATE_FAILURE:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_failure, Toast.LENGTH_LONG).show();
                    mNotificationManager.cancel(NOTIFICATION_ID);
                    break;
                case DOWNLOAD_STATE_START:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_start, Toast.LENGTH_LONG).show();
                    break;
                case DOWNLOAD_STATE_INSTALL:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_install, Toast.LENGTH_LONG).show();
                    break;
                case DOWNLOAD_STATE_ERROR_SDCARD:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_error_sdcard, Toast.LENGTH_LONG).show();
                    break;
                case DOWNLOAD_STATE_ERROR_URL:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_error_url, Toast.LENGTH_LONG).show();
                    break;
                default:
                    break;
            }
            return true;
        }
    };
    private Handler mHandler = new Handler(mHandlerCallBack);

    private HttpLess.DownloadCallBack mDownloadCallBack = new HttpLess.DownloadCallBack() {

        @Override
        public void onDownloading(int progress) {
            if (progress % $.sNotificationFrequent == 0 || progress == 1 || progress == 100) {
                mNotification.contentView.setProgressBar(R.id.less_app_update_progressbar, 100, progress, false);
                mNotification.contentView.setTextViewText(R.id.less_app_update_progress_text, progress + "%");
                mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            }
        }

        @Override
        public void onDownloaded() {
            mNotification.defaults = Notification.DEFAULT_SOUND;
            mNotification.contentIntent = mPendingIntent;
            mNotification.contentView.setTextViewText(R.id.less_app_update_progress_text, getText(R.string.less_app_download_notification_success));
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            if (mDestFile.exists() && mDestFile.isFile() && checkApkFile(mDestFile.getPath())) {
                Message msg = mHandler.obtainMessage();
                msg.what = DOWNLOAD_STATE_SUCCESS;
                mHandler.sendMessage(msg);
            }
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    };

    int state = 0;
    static final int state_idel = 0;
    static final int state_running = 1;



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (state== state_running)
        {
            Toast.makeText(getApplicationContext(), "正在下载", Toast.LENGTH_LONG).show();
            return super.onStartCommand(intent, flags, startId);
        }
        state = state_running;
        mDownloadUrl = intent.getStringExtra($.KEY_DOWNLOAD_URL);
        if (TextUtils.isEmpty($.sDownloadSDPath)) {
            mDownloadSDPath = getPackageName() + "/download";
        } else {
            mDownloadSDPath = $.sDownloadSDPath;
        }

        if (TextUtils.isEmpty(mDownloadUrl)) {
            sendMessage(DOWNLOAD_STATE_ERROR_URL);
            return super.onStartCommand(intent, flags, startId);
        }

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mDestDir = new File(Environment.getExternalStorageDirectory().getPath()
                    + "/" + mDownloadSDPath);
            if (mDestDir.exists()) {
                File destFile = new File(mDestDir.getPath() + "/" + URLEncoder.encode(mDownloadUrl));
                if (destFile.exists() && destFile.isFile() && checkApkFile(destFile.getPath())) {

                    sendMessage(DOWNLOAD_STATE_INSTALL);
                    install(destFile);
                    stopSelf();
                    state = state_idel;
                    return super.onStartCommand(intent, flags, startId);
                }
            }
        } else {
            sendMessage(DOWNLOAD_STATE_ERROR_SDCARD);
            return super.onStartCommand(intent, flags, startId);
        }

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotification = new Notification();

        mNotification.contentView = new RemoteViews(getApplication().getPackageName(), R.layout.less_app_update_notification);

        Intent completingIntent = new Intent();
        completingIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        completingIntent.setClass(getApplicationContext(), UpdateService.class);

        mPendingIntent = PendingIntent.getActivity(UpdateService.this, R.string.less_app_name, completingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification.icon = $.sUpdateIcon != 0 ? $.sUpdateIcon : R.drawable.less_app_update_icon;
        mNotification.tickerText = getText(R.string.less_app_download_notification_start);
        mNotification.contentIntent = mPendingIntent;
        mNotification.contentView.setProgressBar(R.id.less_app_update_progressbar, 100, 0, false);
        mNotification.contentView.setTextViewText(R.id.less_app_update_progress_text, "0%");
        if ($.sUpdateIcon != 0) {
            mNotification.contentView.setImageViewResource(R.id.less_app_update_progress_icon, $.sUpdateIcon);
        }
        // arthas add start
            mNotification.contentView.setTextViewText(R.id.less_app_update_progress_title, AppLess.$appname());
        // arthas add end
        mNotificationManager.cancel(NOTIFICATION_ID);
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);

        // 启动线程开始下载
        new UpdateThread().start();

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * 检查apk文件是否有效(是正确下载,没有损坏的)
     * @param apkFilePath
     * @return
     */
    public boolean checkApkFile(String apkFilePath) {
        boolean result;
        try {
            PackageManager pManager = getPackageManager();
            PackageInfo pInfo = pManager.getPackageArchiveInfo(apkFilePath, PackageManager.GET_ACTIVITIES);
            if (pInfo == null) {
                result = false;
            } else {
                result = true;
            }
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 调用系统Intent安装apk包
     * @param apkFile
     */
    private void install(File apkFile) {
        Uri uri = Uri.fromFile(apkFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void sendMessage(int what) {
        Message msg = mHandler.obtainMessage();
        msg.what = what;
        mHandler.sendMessage(msg);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 下载线程
     */
    class UpdateThread extends Thread {

        @Override
        public void run() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                if (mDestDir == null) {
                    mDestDir = new File(Environment.getExternalStorageDirectory().getPath()
                            + "/" + mDownloadSDPath);
                }

                if (mDestDir.exists() && !mDestDir.isDirectory()) {
                    mDestDir.delete();
                }

                if (mDestDir.exists() || mDestDir.mkdirs()) {
                    mDestFile = new File(mDestDir.getPath()
                            + "/" + URLEncoder.encode(mDownloadUrl));

                    if (mDestFile.exists()
                            && mDestFile.isFile()
                            && checkApkFile(mDestFile.getPath())) {
                        sendMessage(DOWNLOAD_STATE_INSTALL);
                        install(mDestFile);
                    } else {
                        try {
                            sendMessage(DOWNLOAD_STATE_START);
                            HttpLess.$download(mDownloadUrl, mDestFile, false, mDownloadCallBack);
                        } catch (Exception e) {
                            sendMessage(DOWNLOAD_STATE_FAILURE);
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                sendMessage(DOWNLOAD_STATE_ERROR_SDCARD);
            }
            state = state_idel;
            stopSelf();
        }
    }
}
