package com.example.android_navigation_demo.kakalauncher;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android_navigation_demo.R;
import com.example.android_navigation_demo.adapter.BaseFragmentAdapter;
import com.example.android_navigation_demo.fragment.LauncherBaseFragment;
import com.example.android_navigation_demo.fragment.PrivateMessageLauncherFragment;
import com.example.android_navigation_demo.fragment.RewardLauncherFragment;
import com.example.android_navigation_demo.fragment.StereoscopicLauncherFragment;
import com.example.android_navigation_demo.view.GuideViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zt on 2016/7/3.
 */
public class KaKaLauncherActivity extends FragmentActivity {
    private GuideViewPager vPager;
    private List<LauncherBaseFragment> list = new ArrayList<>();
    private BaseFragmentAdapter adapter;

    private ImageView[] tips;
    private int currentSelect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luancher_main);
        //初始化点点点控件
        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        tips = new ImageView[3];

        for (int i = 0; i < tips.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new LayoutParams(10, 10));
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips[i] = imageView;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 20;//设置点点点view的左边距
            layoutParams.rightMargin = 20;//设置点点点view的右边距
            group.addView(imageView, layoutParams);
        }
        //获取自定义viewpager 然后设置背景图片
        vPager = (GuideViewPager) findViewById(R.id.viewpager_launcher);
        vPager.setBackGroud(BitmapFactory.decodeResource(getResources(), R.drawable.bg_kaka_launcher));

        /**
         * 初始化三个fragment  并且添加到list中
         */
        RewardLauncherFragment rewardFragment = new RewardLauncherFragment();
        PrivateMessageLauncherFragment privateFragment = new PrivateMessageLauncherFragment();
        StereoscopicLauncherFragment stereoscopicFragment = new StereoscopicLauncherFragment();
        list.add(rewardFragment);
        list.add(privateFragment);
        list.add(stereoscopicFragment);
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), list);
        vPager.setAdapter(adapter);
        vPager.setOffscreenPageLimit(2);
        vPager.setCurrentItem(0);
        vPager.setOnPageChangeListener(changeListener);
    }

    /**
     * 监听viewpager的移动
     */
    private OnPageChangeListener changeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int index) {
            setImageBackground(index);//改变点点点的切换效果
            LauncherBaseFragment fragment = list.get(index);
            list.get(currentSelect).stopAnimation();//停止前一个页面的动画
            fragment.startAnimation();//开启当前页面的动画
            currentSelect = index;
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    /**
     * 改变点点点的切换效果
     *
     * @param selectItems
     */

    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

}
