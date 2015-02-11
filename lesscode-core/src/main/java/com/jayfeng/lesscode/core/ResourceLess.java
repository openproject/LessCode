package com.jayfeng.lesscode.core;

import android.content.Context;
import android.content.res.Resources;

public class ResourceLess {

    public static final String TYPE_ID = "id";
    public static final String TYPE_DRAWABLE = "drawable";

    public static int $id(Context context, String resourceName, String type) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, type, context.getPackageName());
    }

}
