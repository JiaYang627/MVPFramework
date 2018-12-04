package com.jiayang.mvp.mvpframework.m.component;

import com.jiayang.mvp.mvpframework.m.model.ApiModule;
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
}
