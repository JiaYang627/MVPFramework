package com.jiayang.mvp.mvpframework.common;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jiayang.mvp.mvpframework.BuildConfig;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.m.component.AppComponent;
import com.jiayang.mvp.mvpframework.m.component.DaggerApiComponent;
import com.jiayang.mvp.mvpframework.m.component.DaggerAppComponent;
import com.jiayang.mvp.mvpframework.m.model.ApiModule;
import com.jiayang.mvp.mvpframework.m.model.AppModule;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MVPAppDelegate {

    @Inject
    ActivityDelegate activityDelegate;


    private Application appContext;
    private ApiComponent apiComponent;
    private AppComponent appComponent;

    public MVPAppDelegate(Application appContext) {
        this.appContext = appContext;
    }

    public void onCreate() {
        initInject();
        initLogs();
        ToastUtils.init(appContext);
        Stetho.initializeWithDefaults(appContext);

    }

    private void initLogs() {
        LogUtils.setLogEnable(BuildConfig.DEBUG);
    }

    private void initInject() {
        appComponent = DaggerAppComponent.builder()
                .appModule(getAppModule())
                .build();


        apiComponent = DaggerApiComponent.builder()
                .apiModule(new ApiModule())
                .appModule(getAppModule())
                .build();

    }


    private AppModule getAppModule() {
        return new AppModule(appContext);
    }

    public ApiComponent getApiComponent() {

        return apiComponent;
    }

    public void onTerminate() {
        this.appComponent = null;
        this.apiComponent = null;
        this.appComponent = null;
    }
}
