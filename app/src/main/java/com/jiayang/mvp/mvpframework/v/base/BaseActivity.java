package com.jiayang.mvp.mvpframework.v.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jiayang.mvp.mvpframework.common.MVPApp;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.base.BasePresenter;
import com.jiayang.mvp.mvpframework.utils.PermissionUtils;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public abstract class BaseActivity <T extends BasePresenter> extends RxAppCompatActivity implements IBaseView {
    protected Activity context;
    private Unbinder unbinder;
    @Inject
    protected T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = this;
        inject(((MVPApp)getApplication()).getApiComponent());
        mPresenter.attachView(this);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mPresenter.getContext(context);             //此方法是给P 传递 当前Act的上下文 必须写在getData前面
        mPresenter.getData(getIntent());

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onTakeView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, requestCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    protected abstract void inject(ApiComponent apiComponent);
    protected abstract int getLayoutId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        unbinder.unbind();
    }
}
