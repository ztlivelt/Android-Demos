package com.example.android_theme_demo.attr;

import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.widget.AbsListView;

import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class ListSelectorAttr extends BaseAttr {
    @Override
    public void apply(View view) {
        if (null != view && view instanceof AbsListView){
            AbsListView absListView = (AbsListView) view;
            if (isColorType()){
                int result = SkinManager.getInstance().getColor(attrValue);
                if ( 0 != result) {
                    absListView.setSelector(result);
                } else {
                    absListView.setSelector(new BitmapDrawable());
                }
            } else if (isDrawableType()) {
                absListView.setSelector(SkinManager.getInstance().getDrawable(attrValue));
            }
        }
    }
}
