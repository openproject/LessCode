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
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.io.File;
import java.net.URLEncoder;

public class UpdateService extends Service {

    private static final int DOWNLOAD_STATE_FAILURE = -1;
    private static final int DOWNLOAD_STATE_SUCCESS = 0;

    private static final int NOTIFICATION_ID = 3956;
    private NotificationManager mNotificationManager = null;
    private Notification mNotification = null;
    private PendingIntent mPendingIntent = null;

    private String mDownloadSDPath;
    private String mDownloadUrl;
    private File mDestDir;
    private File mDestFile;

    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD_STATE_SUCCESS:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_success, Toast.LENGTH_LONG).show();
                    install(mDestFile);
                    break;
                case DOWNLOAD_STATE_FAILURE:
                    Toast.makeText(getApplicationContext(), R.string.less_app_download_failure, Toast.LENGTH_LONG).show();
                    mNotificationManager.cancel(NOTIFICATION_ID);
                    break;
                default:
                    break;
            }
        }

    };

    private HttpLess.DownloadCallBack mDownloadCallBack = new HttpLess.DownloadCallBack() {

        @Override
        public void onDownloading(int progress) {
            mNotification.contentView.setProgressBar(R.id.less_app_update_progressbar, 100, progress, false);
            mNotification.contentView.setTextViewText(R.id.less_app_update_progress_text, progress + "%");
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        }

        @Override
        public void onDownloaded() {
            mNotification.contentView.setViewVisibility(R.id.less_app_update_progress_block, View.GONE);
            mNotification.defaults = Notification.DEFAULT_SOUND;
            mNotification.contentIntent = mPendingIntent;
            mNotification.contentView.setTextViewText(R.id.less_app_update_progress_text, "下载完成。");
            mNotificationManager.notify(NOTIFICATION_ID, mNotification);
            if (mDestFile.exists() && mDestFile.isFile() && checkApkFile(mDestFile.getPath())) {
                Message msg = mHandler.obtainMessage();
                msg.what = DOWNLOAD_STATE_SUCCESS;
                mHandler.sendMessage(msg);
            }
            mNotificationManager.cancel(NOTIFICATION_ID);
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mDownloadUrl = intent.getStringExtra($.KEY_DOWNLOAD_URL);
        mDownloadSDPath = getPackageName() + "/download";

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            mDestDir = new File(Environment.getExternalStorageDirectory().getPath()
                    + "/" + mDownloadSDPath);
            if (mDestDir.exists()) {
                File destFile = new File(mDestDir.getPath() + "/" + URLEncoder.encode(mDownloadUrl));
                if (destFile.exists() && destFile.isFile() && checkApkFile(destFile.getPath())) {
                    install(destFile);
                    stopSelf();
                    return super.onStartCommand(intent, flags, startId);
                }
            }
        } else {
            return super.onStartCommand(intent, flags, startId);
        }

        mNotificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mNotification = new Notification();

        mNotification.contentView = new RemoteViews(getApplication().getPackageName(), R.layout.less_app_update_notification);

        Intent completingIntent = new Intent();
        completingIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        completingIntent.setClass(getApplicationContext(), UpdateService.class);

        mPendingIntent = PendingIntent.getActivity(UpdateService.this, R.string.less_app_name, completingIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mNotification.icon = R.drawable.less_app_update_icon;
        mNotification.tickerText = "开始下载";
        mNotification.contentIntent = mPendingIntent;
        mNotification.contentView.setProgressBar(R.id.less_app_update_progressbar, 100, 0, false);
        mNotification.contentView.setTextViewText(R.id.less_app_update_progress_text, "0%");
        mNotificationManager.cancel(NOTIFICATION_ID);
        mNotificationManager.notify(NOTIFICATION_ID, mNotification);
        new UpdateThread().start();


        return super.onStartCommand(intent, flags, startId);
    }

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

    public void install(File apkFile) {
        Uri uri = Uri.fromFile(apkFile);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class UpdateThread extends Thread {

        @Override
        public void run() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                if (mDestDir == null) {
                    mDestDir = new File(Environment.getExternalStorageDirectory().getPath()
                            + "/" + mDownloadSDPath);
                }
                if (mDestDir.exists() || mDestDir.mkdirs()) {
                    mDestFile = new File(mDestDir.getPath()
                            + "/" + URLEncoder.encode(mDownloadUrl));
                    if (mDestFile.exists()
                            && mDestFile.isFile()
                            && checkApkFile(mDestFile.getPath())) {
                        install(mDestFile);
                    } else {
                        try {
                            HttpLess.$download(mDownloadUrl, mDestFile, false, mDownloadCallBack);
                        } catch (Exception e) {
                            Message msg = mHandler.obtainMessage();
                            msg.what = DOWNLOAD_STATE_FAILURE;
                            mHandler.sendMessage(msg);
                            e.printStackTrace();
                        }
                    }
                }
            }
            stopSelf();
        }
    }
}
