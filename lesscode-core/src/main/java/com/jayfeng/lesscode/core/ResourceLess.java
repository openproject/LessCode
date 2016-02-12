package com.jayfeng.lesscode.core;

import android.content.Context;
import android.content.res.Resources;

public final class ResourceLess {

    /**
     * 根据资源名称和类型,得到资源ID
     * @param context
     * @param resourceName
     * @param type
     * @return
     */
    public static int $id(Context context, String resourceName, TYPE type) {
        Resources resources = context.getResources();
        return resources.getIdentifier(resourceName, type.getString(), context.getPackageName());
    }

    /**
     * 定义资源枚举类型
     */
    public enum TYPE {
        ATTR("attr"),
        ARRAY("array"),
        ANIM("anim"),
        BOOL("bool"),
        COLOR("color"),
        DIMEN("dimen"),
        DRAWABLE("drawable"),
        ID("id"),
        INTEGER("integer"),
        LAYOUT("layout"),
        MENU("menu"),
        MIPMAP("mipmap"),
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
