package com.example.android_theme_demo.attr;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;

import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class BackgroundAttr extends BaseAttr {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void apply(View view) {
        if ( view != null){
            if(isColorType()){
                view.setBackgroundColor(SkinManager.getInstance().getColor(attrValue));
            } else if (isDrawableType()){
                view.setBackground(SkinManager.getInstance().getDrawable(attrValue));
            }
        }
    }
}
