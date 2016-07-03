package com.example.android_theme_demo.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android_theme_demo.R;
import com.example.android_theme_demo.activity.BaseActivity;
import com.example.android_theme_demo.manager.SkinFactory;
import com.example.android_theme_demo.manager.SkinFactory.*;
import com.example.android_theme_demo.ui.util.CommonUtil;

public class ThirdActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
    }
    private void setContentView(){
        int id = R.color.common_title_bg_color;
        FrameLayout titleLayout = new FrameLayout(this);
        titleLayout.setBackgroundColor(getResources().getColor(id));
        createSkinView(titleLayout, id, AttrName.background, EntryType.color);

        id = R.color.common_title_text_color;
        TextView textView = new TextView(this);
        textView.setText("第三个页面");
        textView.setTextColor(getResources().getColor(id));
        textView.setTextSize(18);
        createSkinView(textView, id, AttrName.textColor, EntryType.color);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-2, -2);
        params.gravity = Gravity.CENTER;
        titleLayout.addView(textView, params);

        id = R.color.common_bg_color;
        FrameLayout rootView = new FrameLayout(this);
        rootView.setBackgroundColor(getResources().getColor(id));
        params = new FrameLayout.LayoutParams(-1, CommonUtil.dip2px(this, 65));
        rootView.addView(titleLayout, params);
        createSkinView(rootView, id, AttrName.background, EntryType.color);

        params = new FrameLayout.LayoutParams(-1, -1);
        setContentView(rootView, params);
    }
}
