package com.jiayang.mvp.mvpframework.m.model;

import android.app.Application;

import com.jiayang.mvp.mvpframework.common.AppNavigate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 张 奎 on 2017-09-01 08:58.
 */
@Module
public class AppModule {


    private Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Singleton
    @Provides
    public Application provideApplication() {
        return mApplication;
    }

    @Singleton
    @Provides
    AppNavigate provideWyNavigate() {
        return new AppNavigate();
    }
}
