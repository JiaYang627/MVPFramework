package com.jiayang.mvp.mvpframework.v.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RelativeLayout;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.Constants;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.utils.DialogUtils;
import com.jiayang.mvp.mvpframework.utils.PermissionUtils;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.MainActivityViewIpm;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPst> implements MainActivityViewIpm {

    @BindView(R.id.activity_main)
    RelativeLayout mActivityMain;


    //必须的权限 预防6.0动态权限   此处模拟 两个权限
    public String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
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

        checkPermission();

    }
    private void checkPermission() {
        PermissionUtils.requestPermissions(this, Constants.PERMISSION_REQUEST_CODE, permissions, new PermissionUtils.OnPermissionListener() {
            @Override
            public void onPermissionGranted(List<String> granted) {
                DialogUtils.dismissDialogRequestPermission();
                if (granted.size() == permissions.length) {
//                    mPresenter.getHomeInfo();     再次请求数据
                }
            }

            @Override
            public void onPermissionDenied(List<String> denied) {
                DialogUtils.showDialogRequestPermission(MainActivity.this);
            }
        });
    }

    public void button(View view) {
        mPresenter.show();
    }

    @Override
    public void show() {

    }

}
