package com.jayfeng.lesscode.core.other;

import android.view.View;

import java.util.Calendar;

/**
 * 防止快速点击的ClickListener
 */
public abstract class ViewThrottleClickListener implements View.OnClickListener {
    private static final int THROTTLE_TIME_DEFAULT = 1000; // 1s
    private long mLastClickTime = 0;

    public long getThrottleTime() {
        return THROTTLE_TIME_DEFAULT;
    }

    public abstract void throttleClick(View view);

    @Override
    public void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - mLastClickTime > getThrottleTime()) {
            mLastClickTime = currentTime;
            throttleClick(v);
        }
    }
}
