package com.example.android_theme_demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.example.android_theme_demo.listener.ISkinUpdate;
import com.example.android_theme_demo.manager.SkinFactory;
import com.example.android_theme_demo.manager.SkinFactory.*;
import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class BaseActivity extends AppCompatActivity implements ISkinUpdate{
    public LayoutInflater mInflater;
    private SkinFactory mFactory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        mFactory = new SkinFactory();
        mInflater = getLayoutInflater();
        mInflater.setFactory(mFactory);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinManager.getInstance().onAttach(this);
    }

    @Override
    protected void onDestroy() {
        destroySkinRes();
        super.onDestroy();
    }
    public final void destroySkinRes() {
        if(null != mFactory) {
            mFactory.onDestroy();
        }
        mFactory = null;
        mInflater = null;
        SkinManager.getInstance().onDettach(this);
    }
    public final void createSkinView(View view, int id, AttrName attrName, EntryType entryType) {
        mFactory.createSkinView(view, attrName, "", id, "", entryType);
    }
    @Override
    public void updateSkin() {
        mFactory.applySkin();
    }
}
