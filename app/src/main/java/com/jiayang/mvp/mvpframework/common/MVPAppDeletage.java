package com.jiayang.mvp.mvpframework.common;

import android.app.Application;

import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.m.component.AppComponent;
import com.jiayang.mvp.mvpframework.m.component.DaggerApiComponent;
import com.jiayang.mvp.mvpframework.m.component.DaggerAppComponent;
import com.jiayang.mvp.mvpframework.m.model.ApiModule;
import com.jiayang.mvp.mvpframework.m.model.AppModule;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MVPAppDeletage {

    @Inject
    ActivityDelegate activityDelegate;


    private Application appContext;
    private ApiComponent apiComponent;
    private AppComponent appComponent;

    public MVPAppDeletage(Application appContext) {
        this.appContext = appContext;
    }

    public void onCreate() {
        initInject();
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

    public ApiComponent getApiComponent(){

        return apiComponent;
    }

    public void onTerminate() {
        this.appComponent = null;
        this.apiComponent = null;
        this.appComponent = null;
    }
}
