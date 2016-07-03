package com.example.android_theme_demo.attr;

import android.view.View;
import android.widget.ImageView;

import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class SrcAttr extends BaseAttr {
    @Override
    public void apply(View view) {
        if (null != view){
            if (view instanceof ImageView) {
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(SkinManager.getInstance().getDrawable(attrValue));
            }
        }
    }
}
