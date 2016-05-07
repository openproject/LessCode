package com.jayfeng.lesscode.core.other;

import android.os.Handler;

import java.lang.ref.WeakReference;

public abstract class WeakHandler<T> extends Handler {
    private WeakReference<T> mOwner;

    public WeakHandler(T owner) {
        mOwner = new WeakReference<>(owner);
    }

    public T getOwner() {
        return mOwner.get();
    }
}
