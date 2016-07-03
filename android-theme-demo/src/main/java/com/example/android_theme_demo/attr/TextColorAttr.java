package com.example.android_theme_demo.attr;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.TextView;

import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class TextColorAttr extends BaseAttr {
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void apply(View view) {
        if (null != view) {
            if (isColorType()){
                if (view instanceof TextView) {
                    TextView textView = (TextView)view;
                    textView.setTextColor(SkinManager.getInstance().getColor(attrValue));
                } else {
                    view.setBackgroundColor(SkinManager.getInstance().getColor(attrValue));
                }
            } else if (isDrawableType()){
                view.setBackground(SkinManager.getInstance().getDrawable(attrValue));
            }
        }
    }
}
