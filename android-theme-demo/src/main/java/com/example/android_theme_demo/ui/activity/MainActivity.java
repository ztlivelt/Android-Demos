package com.example.android_theme_demo.ui.activity;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.android_theme_demo.R;
import com.example.android_theme_demo.activity.BaseActivity;
import com.example.android_theme_demo.listener.ILoadListener;
import com.example.android_theme_demo.manager.SkinFactory;
import com.example.android_theme_demo.manager.SkinManager;

import java.io.File;

public class MainActivity extends BaseActivity {

    private LayoutInflater mInflater;
    private SkinFactory mFactory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
    }
    public void openSecond(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void updateSkin(View view){
        String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator + "theme" + File.separator +"test.apk";
        SkinManager.getInstance().loadSkin(skinPath);
    }
}
