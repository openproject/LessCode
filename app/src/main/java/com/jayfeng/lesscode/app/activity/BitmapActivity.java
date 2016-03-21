package com.jayfeng.lesscode.app.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.jayfeng.lesscode.app.R;
import com.jayfeng.lesscode.core.BitmapLess;
import com.jayfeng.lesscode.core.DeviceLess;
import com.jayfeng.lesscode.core.DisplayLess;
import com.jayfeng.lesscode.core.ViewLess;

public class BitmapActivity extends Activity {

    private ImageView roundImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        roundImageView = ViewLess.$(this, R.id.image_round);

        Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.girl2)).getBitmap();
        bitmap = BitmapLess.$round(bitmap, 100, true);
        roundImageView.setImageBitmap(bitmap);
    }
}
