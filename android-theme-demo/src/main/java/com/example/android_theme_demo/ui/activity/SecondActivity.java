package com.example.android_theme_demo.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android_theme_demo.R;
import com.example.android_theme_demo.activity.BaseActivity;
import com.example.android_theme_demo.manager.SkinManager;

public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void updateSkin(View view) {
        SkinManager.getInstance().restoreDefaultSkin();
    }
    public void openThird(View view) {
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}
