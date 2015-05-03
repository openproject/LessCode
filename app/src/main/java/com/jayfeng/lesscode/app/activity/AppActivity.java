package com.jayfeng.lesscode.app.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.AppLess;
import com.jayfeng.lesscode.core.ToastLess;

public class AppActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        ToastLess.$(this, "current package:" + AppLess.$running(this, null)
                + ", else package:" + AppLess.$running(this, "com.marriage"));
    }
}
