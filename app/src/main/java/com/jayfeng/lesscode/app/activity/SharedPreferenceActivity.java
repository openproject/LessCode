package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.SharedPreferenceLess;

public class SharedPreferenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);


        SharedPreferenceLess.$put("key1", 333);
        SharedPreferenceLess.$put("key2", 0.5f);
        SharedPreferenceLess.$put("key3", true);
        SharedPreferenceLess.$put("key4", 100000L);
        SharedPreferenceLess.$put("key5", "i am a string");
        SharedPreferenceLess.$put("key5", null);

        LogLess.$d(SharedPreferenceLess.$get("key1", 0) + "");
        LogLess.$d(SharedPreferenceLess.$get("key2", 0f) + "");
        LogLess.$d(SharedPreferenceLess.$get("key3", false) + "");
        LogLess.$d(SharedPreferenceLess.$get("key4", 0L) + "");
        LogLess.$d(SharedPreferenceLess.$get("key5", ""));
    }
}
