package com.example.android_theme_demo.bean;

import android.util.Log;
import android.view.View;

import com.example.android_theme_demo.attr.BaseAttr;

import java.util.List;

/**
 * Created by zt on 2016/7/2.
 */
public class SkinView {
    private static final String TAG = SkinView.class.getSimpleName();
    public View view;
    public List<BaseAttr> viewAttrs;

    public void apply() {
        if (view != null && viewAttrs != null){
            for (BaseAttr attr : viewAttrs) {
                attr.apply(view);
            }
        }
    }
    public void destroy(){
        if (null != viewAttrs){
            for (BaseAttr attr : viewAttrs){
                Log.i(TAG,"destroy skinview : " + attr.toString());
                attr = null;
            }
            viewAttrs.clear();
            viewAttrs = null;
        }
        view = null;
    }
}
