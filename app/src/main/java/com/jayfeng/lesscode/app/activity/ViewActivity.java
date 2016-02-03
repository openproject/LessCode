package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.ViewLess;
import com.jayfeng.lesscode.core.other.ViewThrottleClickListener;

public class ViewActivity extends Activity {

    private Button normalButton;
    private Button throttleButton;
    private Button throttleLongButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        normalButton = ViewLess.$(this, R.id.button_normal);
        throttleButton = ViewLess.$(this, R.id.button_throttle);
        throttleLongButton = ViewLess.$(this, R.id.button_throttle_long);

        normalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogLess.$d("normal button click");
            }
        });
        throttleButton.setOnClickListener(new ViewThrottleClickListener() {
            @Override
            public void throttleClick(View view) {
                LogLess.$d("throttle click");
            }
        });
        throttleLongButton.setOnClickListener(new ViewThrottleClickListener() {
            @Override
            public void throttleClick(View view) {
                LogLess.$d("throttle click 3s");
            }

            @Override
            public long getThrottleTime() {
                return 3000;
            }
        });
    }
}
