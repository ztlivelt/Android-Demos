package com.example.android_navigation_demo.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.android_navigation_demo.fragment.LauncherBaseFragment;

import java.util.List;

/**
 * Viewpager适配器
 * Created by zt on 2016/7/3.
 */
public class BaseFragmentAdapter extends FragmentStatePagerAdapter {
    private List<LauncherBaseFragment> list;
    public BaseFragmentAdapter(FragmentManager fm, List<LauncherBaseFragment> list ){
        super(fm);
        this.list = list;
    }
    public BaseFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
