package com.jayfeng.lesscode.core.other;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * RecyclerView的ItemDecoration的默认实现
 * 1. 默认使用系统的分割线
 * 2. 支持自定义Drawable类型
 * 3. 支持水平和垂直方向
 * 4. 修复了官方垂直Divider显示的bug
 * 5. 增加支持GRID_LIST的分割线, 但仅支持空视图分割(如果嫌弃左右上下多余的padding, 可以在RecyclerView中设置padding为负值解决)
 * 扩展自官方android sdk下的Support7Demos下的DividerItemDecoration
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    public static final int GRID_LIST = LinearLayoutManager.VERTICAL + 1;

    private Drawable mDivider;
    private int mWidth;
    private int mHeight;

    private int mOrientation;

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
    }

    /**
     * 新增：支持自定义dividerDrawable
     *
     * @param context
     * @param orientation
     * @param dividerDrawable
     */
    public DividerItemDecoration(Context context, int orientation, Drawable dividerDrawable) {
        mDivider = dividerDrawable;
        setOrientation(orientation);
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST
                && orientation != VERTICAL_LIST
                && orientation != GRID_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    /**
     * 新增：支持手动为无高宽的drawable制定宽度
     *
     * @param width
     */
    public void setWidth(int width) {
        this.mWidth = width;
    }

    /**
     * 新增：支持手动为无高宽的drawable制定高度
     *
     * @param height
     */
    public void setHeight(int height) {
        this.mHeight = height;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else if (mOrientation == HORIZONTAL_LIST) {
            drawHorizontal(c, parent);
        }
    }

    public void drawVertical(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            int bottom = top + getDividerHeight();


            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            int left = child.getRight() + params.rightMargin +
                    Math.round(ViewCompat.getTranslationX(child));
            int right = left + getDividerWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (mOrientation == VERTICAL_LIST) {
            outRect.set(0, 0, 0, getDividerHeight());
        } else if (mOrientation == HORIZONTAL_LIST) {
            outRect.set(0, 0, getDividerWidth(), 0);
        } else if (mOrientation == GRID_LIST) {
            outRect.set(getDividerWidth(), getDividerHeight(), getDividerWidth(), getDividerHeight());
        }
    }

    private int getDividerWidth() {
        return mWidth > 0 ? mWidth : mDivider.getIntrinsicWidth();
    }

    private int getDividerHeight() {
        return mHeight > 0 ? mHeight : mDivider.getIntrinsicHeight();
    }

}
