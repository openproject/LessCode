package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.AdapterLess;
import com.jayfeng.lesscode.core.AppLess;
import com.jayfeng.lesscode.core.DrawableLess;
import com.jayfeng.lesscode.core.ToastLess;
import com.jayfeng.lesscode.core.ViewLess;

public class DrawableActivity extends Activity {

    private static final PorterDuff.Mode[] tintModeArray = {
//            PorterDuff.Mode.ADD,
            PorterDuff.Mode.CLEAR,
            PorterDuff.Mode.DARKEN,
            PorterDuff.Mode.DST,
            PorterDuff.Mode.DST_ATOP,
            PorterDuff.Mode.DST_IN,
            PorterDuff.Mode.DST_OUT,
            PorterDuff.Mode.DST_OVER,
            PorterDuff.Mode.LIGHTEN,
            PorterDuff.Mode.MULTIPLY,
//            PorterDuff.Mode.OVERLAY,
            PorterDuff.Mode.SCREEN,
            PorterDuff.Mode.SRC,
            PorterDuff.Mode.SRC_ATOP,
            PorterDuff.Mode.SRC_IN,
            PorterDuff.Mode.SRC_OUT,
            PorterDuff.Mode.SRC_OVER,
            PorterDuff.Mode.XOR
    };

    private ImageView imageView;
    private Spinner tintModeView;

    private ArrayAdapter<PorterDuff.Mode> tintModeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawable);

        imageView = ViewLess.$(this, R.id.image);
        tintModeView = ViewLess.$(this, R.id.tintMode);

        tintModeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tintModeArray);
        tintModeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tintModeView.setAdapter(tintModeAdapter);

        tintModeView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Drawable tintDrawable = DrawableLess.$tint(
                        getResources().getDrawable(R.drawable.ic_launcher),
                        ColorStateList.valueOf(Color.RED),
                        tintModeArray[position]
                );

                ToastLess.$(DrawableActivity.this, tintModeArray[position].name());

                imageView.setImageDrawable(tintDrawable);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
