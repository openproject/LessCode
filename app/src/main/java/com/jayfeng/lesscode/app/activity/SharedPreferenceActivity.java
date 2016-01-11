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


        SharedPreferenceLess.$put(this, "key1", 333);
        SharedPreferenceLess.$put(this, "key2", 0.5f);
        SharedPreferenceLess.$put(this, "key3", true);
        SharedPreferenceLess.$put(this, "key4", 100000L);
        SharedPreferenceLess.$put(this, "key5", "i am a string");
        SharedPreferenceLess.$put(this, "key5", null);

        LogLess.$d(SharedPreferenceLess.$get(this, "key1", 0) + "");
        LogLess.$d(SharedPreferenceLess.$get(this, "key2", 0f) + "");
        LogLess.$d(SharedPreferenceLess.$get(this, "key3", false) + "");
        LogLess.$d(SharedPreferenceLess.$get(this, "key4", 0L) + "");
        LogLess.$d(SharedPreferenceLess.$get(this, "key5", ""));
    }
}
