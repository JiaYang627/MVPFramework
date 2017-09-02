package com.jiayang.mvp.mvpframework.m.component;

import com.jiayang.mvp.mvpframework.m.model.ApiModule;
import com.jiayang.mvp.mvpframework.v.activity.MainActivity;
import com.jiayang.mvp.mvpframework.v.activity.TestActivity;

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
}
