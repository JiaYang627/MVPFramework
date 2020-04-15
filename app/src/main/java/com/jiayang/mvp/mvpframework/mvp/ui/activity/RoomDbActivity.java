package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.MVPApp;
import com.jiayang.mvp.mvpframework.database.AppDatabase;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.RoomDbViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.RoomDbPresenter;


public class RoomDbActivity extends BaseActivity<RoomDbPresenter> implements RoomDbViewIpm {


    private AppDatabase mAppDataBase;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_room_db;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mAppDataBase = ((MVPApp) getApplication()).getAppDataBase();
    }
}
