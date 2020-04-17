package com.jiayang.mvp.mvpframework.m.component;

import com.jiayang.mvp.mvpframework.m.model.ApiModule;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.ComposeVideoAndAudioActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.CustomActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.RoomDbActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.SimpleFFmpegActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.SpannableActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.SplashActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.TestKotlinTempActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.VideoPlayerActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.ZXingActivity;
import com.jiayang.mvp.mvpframework.v.activity.ChangeBaseUrlActivity;
import com.jiayang.mvp.mvpframework.v.activity.MainActivity;
import com.jiayang.mvp.mvpframework.v.activity.NumAnimActivity;
import com.jiayang.mvp.mvpframework.v.activity.TestActivity;
import com.jiayang.mvp.mvpframework.v.activity.TimeSelectActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/8/31 0031.
 */
@Singleton
@Component(modules = {ApiModule.class})
public interface ApiComponent {
    void inject(MainActivity mainActivity);

    void inject(TestActivity testActivity);

    void inject(NumAnimActivity numAnimActivity);

    void inject(TimeSelectActivity timeSelectActivity);

    void inject(ChangeBaseUrlActivity changeBaseUrlActivity);

    void inject(ZXingActivity zXingActivity);

    void inject(SpannableActivity spannableActivity);

    void inject(CustomActivity customActivity);

    void inject(SplashActivity splashActivity);

    void inject(VideoPlayerActivity videoPlayerActivity);

    void inject(SimpleFFmpegActivity simpleFFmpegActivity);

    void inject(ComposeVideoAndAudioActivity composeVideoAndAudioActivity);

    void inject(RoomDbActivity roomDbActivity);

    void inject(TestKotlinTempActivity testKotlinTempActivity);
}
