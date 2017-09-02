package com.jiayang.mvp.mvpframework.v.activity;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.v.iview.ImainAcitivityView;

public class MainActivity extends BaseActivity<MainActivityPst> implements ImainAcitivityView {




    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void button(View view) {
        mPresenter.show();
    }
    @Override
    public void show() {

    }
}
