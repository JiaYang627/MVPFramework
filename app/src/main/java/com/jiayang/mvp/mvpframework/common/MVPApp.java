package com.jiayang.mvp.mvpframework.common;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.jiayang.mvp.mvpframework.database.AppDatabase;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MVPApp extends Application {

    private MVPAppDelegate mvpAppDeletage;

    @Override
    public void onCreate() {
        super.onCreate();
        mvpAppDeletage = new MVPAppDelegate(this);
        mvpAppDeletage.onCreate();
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    public AppDatabase getAppDataBase() {
        return mvpAppDeletage.getAppDatabase();
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
