package com.example.android_navigation_demo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.android_navigation_demo.R;

/**
 * Created by zt on 2016/7/3.
 */
public class PrivateMessageLauncherFragment extends  LauncherBaseFragment{
    private ImageView ivLikeVideo,ivThinkReward,ivThisWeek,ivWatchMovie;
    private Animation likeAnimation,thinkAnimation,watchAnimation,thisWeekAnimation;
    private boolean started;//是否开启动画

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_private_message_launcher,null);
        ivLikeVideo=(ImageView) rootView.findViewById(R.id.iv_private_message_like_video);
        ivThinkReward=(ImageView) rootView.findViewById(R.id.iv_private_message_think_reward);
        ivWatchMovie=(ImageView) rootView.findViewById(R.id.iv_private_message_watch_movie);
        ivThisWeek=(ImageView) rootView.findViewById(R.id.private_message_this_week);
        return rootView;
    }


    @Override
    public void stopAnimation() {
        //动画开启标示符设置成false
        started=false;
        /**
         * 清空所有控件上的动画
         */
        ivLikeVideo.clearAnimation();
        ivThinkReward.clearAnimation();
        ivWatchMovie.clearAnimation();
        ivThisWeek.clearAnimation();
    }
    @Override
    public void startAnimation() {
        started=true;

        /**
         * 每次开启动画前先隐藏控件
         */
        ivLikeVideo.setVisibility(View.GONE);
        ivThinkReward.setVisibility(View.GONE);
        ivWatchMovie.setVisibility(View.GONE);
        ivThisWeek.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {//延时0.5秒之后开启喜欢视频动画
            @Override
            public void run(){
                if(started)
                    likeVideoAnimation();
            }
        },500);
    }
    /**
     * 好喜欢你的视频
     */
    private void likeVideoAnimation(){
        ivLikeVideo.setVisibility(View.VISIBLE);

        likeAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.private_message_launcher);
        ivLikeVideo.startAnimation(likeAnimation);//开启动画
        likeAnimation.setAnimationListener(new AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {//监听动画结束
                if(started)
                    thinkReward();
            }
        });
    }

    /**
     * 谢谢你的打赏
     */
    private void thinkReward(){
        ivThinkReward.setVisibility(View.VISIBLE);
        thinkAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.private_message_launcher);
        ivThinkReward.startAnimation(thinkAnimation);
        thinkAnimation.setAnimationListener(new AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                if(started)
                    watchMovie();
            }
        });
    }

    /**
     * 一起看个电影呗
     */
    private void watchMovie(){
        ivWatchMovie.setVisibility(View.VISIBLE);
        watchAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.private_message_launcher);
        ivWatchMovie.startAnimation(watchAnimation);
        watchAnimation.setAnimationListener(new AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                if(started)
                    thisWeek();
            }
        });
    }

    /**
     * 好啊  这周末有空
     */
    private void thisWeek(){
        ivThisWeek.setVisibility(View.VISIBLE);
        thisWeekAnimation = AnimationUtils.loadAnimation(getActivity(),R.anim.private_message_launcher);
        ivThisWeek.startAnimation(thisWeekAnimation);
    }
}
