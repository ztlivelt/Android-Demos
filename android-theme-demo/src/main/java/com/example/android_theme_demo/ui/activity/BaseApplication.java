package com.example.android_theme_demo.ui.activity;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy;

import com.example.android_theme_demo.manager.SkinManager;

/**
 * Created by zt on 2016/7/2.
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDialog().build());
            StrictMode.setVmPolicy(new VmPolicy.Builder().detectAll().penaltyLog().build());
        }
        SkinManager.getInstance().init(this);
    }
}
