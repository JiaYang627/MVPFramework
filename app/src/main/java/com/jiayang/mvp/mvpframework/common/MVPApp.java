package com.jiayang.mvp.mvpframework.common;

import android.app.Application;

import com.jiayang.mvp.mvpframework.m.component.ApiComponent;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MVPApp extends Application {

    private MVPAppDeletage mvpAppDeletage;

    @Override
    public void onCreate() {
        super.onCreate();
        mvpAppDeletage = new MVPAppDeletage(this);
        mvpAppDeletage.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        mvpAppDeletage.onTerminate();
    }

    public ApiComponent getApiComponent(){
        return mvpAppDeletage.getApiComponent();
    }
}
