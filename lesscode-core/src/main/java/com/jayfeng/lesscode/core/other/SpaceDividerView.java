package com.jayfeng.lesscode.core.other;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.jayfeng.lesscode.core.R;

public class SpaceDividerView extends View {

    private int mSpaceLeft = 0;
    private int mSpaceTop = 0;
    private int mSpaceRight = 0;
    private int mSpaceBottom = 0;
    private int mSpaceColor = Color.TRANSPARENT;

    private Paint mPaint = new Paint();

    public SpaceDividerView(Context context) {
        this(context, null);
    }

    public SpaceDividerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SpaceDividerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SpaceDividerView, defStyleAttr, 0);
        mSpaceLeft = a.getDimensionPixelSize(R.styleable.SpaceDividerView_spaceLeft,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mSpaceTop = a.getDimensionPixelSize(R.styleable.SpaceDividerView_spaceTop,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mSpaceRight = a.getDimensionPixelSize(R.styleable.SpaceDividerView_spaceRight,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mSpaceBottom = a.getDimensionPixelSize(R.styleable.SpaceDividerView_spaceBottom,
                (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mSpaceColor = a.getColor(R.styleable.SpaceDividerView_spaceColor, Color.TRANSPARENT);
        a.recycle();

        mPaint.setColor(mSpaceColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mSpaceLeft > 0) {
            canvas.drawRect(0, 0, mSpaceLeft, getMeasuredHeight(), mPaint);
        }
        if (mSpaceTop > 0) {
            canvas.drawRect(0, 0, getMeasuredWidth(), mSpaceTop, mPaint);
        }
        if (mSpaceRight > 0) {
            canvas.drawRect(getMeasuredWidth() - mSpaceRight, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        }
        if (mSpaceBottom > 0) {
            canvas.drawRect(0, getMeasuredHeight() - mSpaceBottom, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        }
    }

    public void setSpaceLeft(int spaceLeft) {
        this.mSpaceLeft = spaceLeft;
        invalidate();
    }

    public void setSpaceTop(int spaceTop) {
        this.mSpaceTop = spaceTop;
        invalidate();
    }

    public void setSpaceRight(int spaceRight) {
        this.mSpaceRight = spaceRight;
        invalidate();
    }

    public void setSpaceBottom(int spaceBottom) {
        this.mSpaceBottom = spaceBottom;
        invalidate();
    }

    public void setSpaceColor(int spaceColor) {
        this.mSpaceColor = spaceColor;
        invalidate();
    }
}
