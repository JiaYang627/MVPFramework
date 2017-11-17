package com.jiayang.mvp.mvpframework.v.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.ImainAcitivityView;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPst> implements ImainAcitivityView {

    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;

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
