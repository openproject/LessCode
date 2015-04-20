package com.jayfeng.lesscode.app.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.DeviceLess;
import com.jayfeng.lesscode.core.ViewLess;

public class DeviceActivity extends ActionBarActivity {

    private TextView macView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        macView = ViewLess.$(this, R.id.mac);
        macView.setText(DeviceLess.$mac());
    }
}
