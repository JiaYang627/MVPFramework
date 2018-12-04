package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.ZXingViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.ZXingPresenter;


public class ZXingActivity extends BaseActivity<ZXingPresenter> implements ZXingViewIpm {


    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing;
    }

}
