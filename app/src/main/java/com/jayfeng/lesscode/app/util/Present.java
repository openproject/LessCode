package com.jayfeng.lesscode.app.util;

import com.jayfeng.lesscode.core.SingletonLess;

public class Present extends SingletonLess<Present> {
    @Override
    protected Present newInstance() {
        return new Present();
    }
}
