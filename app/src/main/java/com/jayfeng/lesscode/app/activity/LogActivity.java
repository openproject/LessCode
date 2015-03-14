package com.jayfeng.lesscode.app.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.LogLess;

public class LogActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        LogLess.$d("xxxxxxx");
        LogLess.$e("yyyyy");
        LogLess.$e("zzzzzzzzzz");
    }
}
