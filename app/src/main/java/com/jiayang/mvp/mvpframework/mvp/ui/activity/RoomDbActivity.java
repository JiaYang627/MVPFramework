package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.MVPApp;
import com.jiayang.mvp.mvpframework.database.AppDatabase;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.RoomDbViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.RoomDbPresenter;


public class RoomDbActivity extends BaseActivity<RoomDbPresenter> implements RoomDbViewIpm, View.OnClickListener {


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

        findViewById(R.id.button_add_user).setOnClickListener(this::onClick);
        findViewById(R.id.button_get_user).setOnClickListener(this::onClick);
        findViewById(R.id.button_del_user).setOnClickListener(this::onClick);
        findViewById(R.id.button_del_all_user).setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_add_user:
                addUser();
                break;
            case R.id.button_get_user:
                getAllUser();
                break;
            case R.id.button_del_user:
                delOneUser();
                break;
            case R.id.button_del_all_user:
                delAllUser();
                break;
            default:
                break;
        }
    }

    private void addUser() {

    }

    private void getAllUser() {

    }

    private void delOneUser() {

    }

    private void delAllUser() {

    }
}
