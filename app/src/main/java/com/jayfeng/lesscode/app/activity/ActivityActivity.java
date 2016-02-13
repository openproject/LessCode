package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.ActivityLess;

public class ActivityActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityLess.$noTitle(this);
        ActivityLess.$fullScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity);
    }
}
