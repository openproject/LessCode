package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.UpdateLess;

public class UpdateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        String updateJson = "{\n" +
                "      \"vercode\":2,\n" +
                "      \"vername\":\"V1.1\",\n" +
                "      \"download\":\"http://download.ctrip.com/client/apppackage/Ctrip_1108_4008_9z1m24.apk\",\n" +
                "      \"log\":\"upgrade content\"\n" +
                "      }";
        UpdateLess.$check(this, updateJson);
    }
}
