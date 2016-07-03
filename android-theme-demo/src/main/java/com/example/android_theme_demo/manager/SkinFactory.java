
package com.example.android_theme_demo.manager;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.View;

import com.example.android_theme_demo.attr.BackgroundAttr;
import com.example.android_theme_demo.attr.BaseAttr;
import com.example.android_theme_demo.attr.DividerAttr;
import com.example.android_theme_demo.attr.ListSelectorAttr;
import com.example.android_theme_demo.attr.SrcAttr;
import com.example.android_theme_demo.attr.TextColorAttr;
import com.example.android_theme_demo.bean.SkinView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zt on 2016/7/2.
 */
public final class SkinFactory implements Factory {
    public static final String TAG = SkinFactory.class.getSimpleName();
    private static final String DEFAULT_SCHEMA_NAME = "http://schemas.android.com/apk/res-auto";
    private static final String DEFAULT_ATTR_NAME = "enable";

    private List<SkinView> mSkinViews = Collections.synchronizedList(new ArrayList<SkinView>());
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.i(TAG, name +  "============start=====================");
        int attrCounts = attrs.getAttributeCount();
        for (int i = 0; i < attrCounts; i++) {
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            Log.i(TAG, "attrName = " + attrName + "           attrValue = " + attrValue);
        }
        Log.i(TAG, name + "========================end=======================");
        View view = null;
        boolean skinEnable = attrs.getAttributeBooleanValue(DEFAULT_SCHEMA_NAME, DEFAULT_ATTR_NAME, false);
        if (skinEnable) {
            view = createView(name, context, attrs);
            if (view != null) {
                parseAttrs(name, context, attrs, view);
            }
        }
        return view;
    }

    public  View createView(String name, Context context, AttributeSet attrs) {
        View view = null;
        if (-1 == name.indexOf('.')) {
            if ("View".equalsIgnoreCase(name)) {
                view = createView(name, context, attrs, "android.view.");
            }
            if (view == null) {
                view = createView(name, context, attrs, "android.widget.");
            }
            if (view == null) {
                view = createView(name, context, attrs, "android.webkit.");
            }
        } else {
            view = createView(name, context, attrs, null);
        }
        return view;
    }

    private View createView(String name, Context context, AttributeSet attrs, String prefix) {
        View view = null;
        try {
            view = LayoutInflater.from(context).createView(name,prefix,attrs);
        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }
    private void parseAttrs(String name,Context context,AttributeSet attrs,View view){
        int attrCount = attrs.getAttributeCount();
        Resources temp = context.getResources();
        List<BaseAttr> viewAttrs = new ArrayList<>();
        for (int i = 0; i< attrCount;i++){
            String attrName = attrs.getAttributeName(i);
            String attrValue = attrs.getAttributeValue(i);
            if (isSupportedAttr(attrName)){
                if (attrValue.startsWith("@")){
                    int id = Integer.parseInt(attrValue.substring(1));
                    String entryName = temp.getResourceEntryName(id);
                    String entryType = temp.getResourceTypeName(id);
                    BaseAttr viewAttr = createAttr(attrName,attrValue,id,entryName,entryType);
                    if (viewAttr != null){
                        viewAttrs.add(viewAttr);
                    }
                }
            }
        }
        if (viewAttrs.size() > 0){
            createSkinView(view,viewAttrs);
        }
    }
    public void createSkinView(View view, AttrName attrName, String attrValue, int id, String entryName, EntryType entryType) {
        BaseAttr viewAttr = createAttr(attrName.toString(), attrValue, id, entryName, entryType.toString());
        if(null != viewAttr) {
            List<BaseAttr> viewAttrs = new ArrayList<BaseAttr>(1);
            viewAttrs.add(viewAttr);
            createSkinView(view, viewAttrs);
        }
    }
    private void createSkinView(View view,List<BaseAttr> viewAttrs){
        SkinView skinView = new SkinView();
        skinView.view = view;
        skinView.viewAttrs = viewAttrs;
        mSkinViews.add(skinView);
        if(SkinManager.getInstance().isExternalSkin()) {
            skinView.apply();
        }
    }
    private BaseAttr createAttr(String attrName,String attrValue,int id,String entryName,String entryType){
        BaseAttr viewAttr = null;
        if ("background".equalsIgnoreCase(attrName)){
            viewAttr = new BackgroundAttr();
        } else if ("textColor".equalsIgnoreCase(attrName)){
            viewAttr = new TextColorAttr();
        } else if ("divider".equalsIgnoreCase(attrName)){
            viewAttr = new DividerAttr();
        } else if ("listSelector".equalsIgnoreCase(attrName)){
            viewAttr = new ListSelectorAttr();
        } else if ("src".equalsIgnoreCase(attrName)){
            viewAttr = new SrcAttr();
        }
        if ( viewAttr != null){
            viewAttr.attrName = attrName;
            viewAttr.attrValue = id;
            viewAttr.entryName = entryName;
            viewAttr.entryType = entryType;
        }
        return viewAttr;
    }
    private boolean isSupportedAttr(String attrName){
        if ("background".equalsIgnoreCase(attrName)){
            return true;
        } else if ("textColor".equalsIgnoreCase(attrName)){
            return true;
        }else if("divider".equalsIgnoreCase(attrName)) {
            return true;
        } else if("listSelector".equalsIgnoreCase(attrName)) {
            return true;
        } else if("src".equalsIgnoreCase(attrName)) {
            return true;
        }
        return false;
    }

    public void applySkin() {
        if (mSkinViews != null){
            for (SkinView skinView : mSkinViews){
                if (skinView.view != null){
                    skinView.apply();
                }
            }
        }
    }

    public void onDestroy(){
        if (null != mSkinViews){
            for (SkinView skinView : mSkinViews){
                skinView.destroy();
                skinView = null;
            }
            mSkinViews.clear();
            mSkinViews = null;
        }
    }

    public enum EntryType {
        color,
        drawable
    }

    public enum AttrName {
        background,
        textColor,
        divider,
        listSelector,
        src
    }
}
