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
                "      \"vercode\":112,\n" +
                "      \"vername\":\"V1.1\",\n" +
                "      \"download\":\"http://www.apk.anzhi.com/data3/apk/201506/09/3a978f27369b4a8bf6de1270da9871ec_86281300.apk\",\n" +
                "      \"log\":\"upgrade content\"\n" +
                "      }";
        UpdateLess.$check(this, updateJson);
    }
}
