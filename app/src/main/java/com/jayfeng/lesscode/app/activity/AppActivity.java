package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.AppLess;
import com.jayfeng.lesscode.core.FileLess;
import com.jayfeng.lesscode.core.StorageLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.jayfeng.lesscode.core.ViewLess;

import java.io.File;

public class AppActivity extends Activity {

    private EditText deleteDirPathView;
    private Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);

        ToastLess.$(this, "current package:" + AppLess.$running(getPackageName())
                + ", else package:" + AppLess.$running("com.marriage"));

        deleteDirPathView = ViewLess.$(this, R.id.del_dir_path);
        deleteButton = ViewLess.$(this, R.id.del_dir_btn);

        deleteDirPathView.setText(StorageLess.Sdcard.$path().getPath() + "/test");

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = deleteDirPathView.getText().toString();
                File dir = new File(path);
                FileLess.$del(dir, true);
            }
        });
    }
}
