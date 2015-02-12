package com.jayfeng.lesscode.core;

import android.content.Context;
import android.content.res.Resources;

public class ResourceLess {

    public static int $id(Context context, String resourceName, TYPE type) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, type.getString(), context.getPackageName());
    }

    public enum TYPE {
        ATTR("attr"),
        ANIM("anim"),
        BOOL("bool"),
        COLOR("color"),
        DIMEN("dimen"),
        DRAWABLE("drawable"),
        ID("id"),
        INTEGER("integer"),
        LAYOUT("layout"),
        MENU("menu"),
        STRING("string"),
        STYLE("style"),
        STYLEABLE("styleable");

        private String string;
        TYPE(String string) {
            this.string = string;
        }
        public String getString() {
            return string;
        }
    }

}
