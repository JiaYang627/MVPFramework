package com.jiayang.mvp.mvpframework.m.component;

import com.jiayang.mvp.mvpframework.common.MVPAppDelegate;
import com.jiayang.mvp.mvpframework.m.model.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 张 奎 on 2017-09-01 09:02.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MVPAppDelegate mvpAppDeletage);
}
