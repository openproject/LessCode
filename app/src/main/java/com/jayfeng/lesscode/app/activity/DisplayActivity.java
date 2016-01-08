package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.DisplayLess;
import com.jayfeng.lesscode.core.ToastLess;

public class DisplayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ToastLess.$(DisplayActivity.this, "status bar height:" + DisplayLess.$statusBarHeight(DisplayActivity.this)
                        + ", title bar height:" + DisplayLess.$titleBarHeight(DisplayActivity.this));
            }
        }, 2000);
        ToastLess.$(this, "status bar height with reflect:" + DisplayLess.$statusBarHeight(getResources()));
    }
}
