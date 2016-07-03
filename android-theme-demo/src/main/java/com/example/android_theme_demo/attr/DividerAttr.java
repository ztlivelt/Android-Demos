package com.example.android_theme_demo.attr;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ListView;

import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class DividerAttr extends BaseAttr{
    @Override
    public void apply(View view) {
        if (null != view && view instanceof ListView){
            ListView listView = (ListView) view;
            if (isColorType()){
                int color = SkinManager.getInstance().getColor(attrValue);
                ColorDrawable drawable = new ColorDrawable(color);
                listView.setDivider(drawable);
                listView.setDividerHeight(1);
            } else if (isDrawableType()){
                listView.setDivider(SkinManager.getInstance().getDrawable(attrValue));
            }
        }
    }
}
