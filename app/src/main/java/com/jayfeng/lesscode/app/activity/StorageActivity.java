package com.jayfeng.lesscode.app.activity;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.LogLess;
import com.jayfeng.lesscode.core.StorageLess;
import com.jayfeng.lesscode.core.ToastLess;

public class StorageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        ToastLess.$(this, "state:" + StorageLess.Sdcard.$ready());

        LogLess.$d("sdcard total:" + StorageLess.Sdcard.$total() + ", used:" + StorageLess.Sdcard.$used() + ", free:" + StorageLess.Sdcard.$free());
        LogLess.$d("phone total:" + StorageLess.Phone.$total() + ", used:" + StorageLess.Phone.$used() + ", free:" + StorageLess.Phone.$free());

        LogLess.$d("sdcard string total:" + StorageLess.Sdcard.$totalString(this)
                + ", used:" + StorageLess.Sdcard.$usedString(this)
                + ", free:" + StorageLess.Sdcard.$freeString(this));

        if (StorageLess.ExtSdcard.$path() != null) {
            LogLess.$d("extSdcard string total:" + Formatter.formatFileSize(this, StorageLess.ExtSdcard.$total())
                    + ", used:" + Formatter.formatFileSize(this, StorageLess.ExtSdcard.$used())
                    + ", free:" + Formatter.formatFileSize(this, StorageLess.ExtSdcard.$free()));
        } else {
            LogLess.$d("no extSdcard.");
        }
    }
}
