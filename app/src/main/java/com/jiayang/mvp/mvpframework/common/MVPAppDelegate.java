package com.jiayang.mvp.mvpframework.common;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.jiayang.mvp.mvpframework.BuildConfig;
import com.jiayang.mvp.mvpframework.database.AppDatabase;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.m.component.AppComponent;
import com.jiayang.mvp.mvpframework.m.component.DaggerApiComponent;
import com.jiayang.mvp.mvpframework.m.component.DaggerAppComponent;
import com.jiayang.mvp.mvpframework.m.model.ApiModule;
import com.jiayang.mvp.mvpframework.m.model.AppModule;
import com.jiayang.mvp.mvpframework.utils.LogUtils;

import java.io.File;

import javax.inject.Inject;

import static android.os.Environment.DIRECTORY_DOCUMENTS;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MVPAppDelegate {

    @Inject
    ActivityDelegate activityDelegate;


    private Application mApplication;
    private ApiComponent apiComponent;
    private AppComponent appComponent;
    private String LOG_PATH;
    private AppDatabase mAppDatabase;

    public MVPAppDelegate(Application appContext) {
        this.mApplication = appContext;
    }

    public void onCreate() {
        initInject();
        initLogs();
//        Stetho.initializeWithDefaults(mApplication);

        initRoom();
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

    private void initRoom() {

        String daName = "room_info_dev.db";
        if (LOG_PATH == null) {
            LOG_PATH = mApplication.getBaseContext().getExternalFilesDir(DIRECTORY_DOCUMENTS).getAbsolutePath() + File.separator + daName;
        }
        mAppDatabase = Room.databaseBuilder(mApplication, AppDatabase.class, LOG_PATH)
                .build();
    }


    public AppDatabase getAppDatabase() {
        return mAppDatabase;
    }

    private AppModule getAppModule() {
        return new AppModule(mApplication);
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
