package com.jiayang.mvp.mvpframework.v.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.TestActivityPst;
import com.jiayang.mvp.mvpframework.v.iview.TestActivityViewIpm;
import com.jiayang.mvp.mvpframework.common.BaseActivity;

public class TestActivity extends BaseActivity<TestActivityPst> implements TestActivityViewIpm {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void showA(String b) {
        Toast.makeText(context,  b, Toast.LENGTH_SHORT).show();
    }
}
