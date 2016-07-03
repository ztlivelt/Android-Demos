package com.example.android_theme_demo.attr;

import android.view.View;

/**
 * Created by zt on 2016/7/2.
 */
public abstract class BaseAttr {
    public String attrName;
    public int attrValue;
    public String entryName;
    public String entryType;
    public boolean isDrawableType() {
        return "drawable".equalsIgnoreCase(entryType);
    }
    public boolean isColorType(){
        return "color".equalsIgnoreCase(entryType);
    }
    public abstract void apply(View view);
}
