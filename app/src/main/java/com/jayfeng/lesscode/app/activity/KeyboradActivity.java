package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.KeyBoardLess;
import com.jayfeng.lesscode.core.ViewLess;

public class KeyboradActivity extends Activity {

    private EditText editText;
    private Button showKeyboradButton;
    private Button hideKeyboradButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keyborad);

        editText = ViewLess.$(this, R.id.edit);
        showKeyboradButton = ViewLess.$(this, R.id.show);
        hideKeyboradButton = ViewLess.$(this, R.id.hide);

        showKeyboradButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardLess.$show(KeyboradActivity.this, editText);
            }
        });

        hideKeyboradButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KeyBoardLess.$hide(KeyboradActivity.this, editText);
            }
        });

    }
}
