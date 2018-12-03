package com.jiayang.mvp.mvpframework.v.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;

import com.jiayang.commonlibs.commonListAdapter.BaseAdapterHelper;
import com.jiayang.commonlibs.commonListAdapter.QuickAdapter;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.bean.Model;
import com.jiayang.mvp.mvpframework.common.Constants;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.utils.DialogUtils;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.PermissionUtils;
import com.jiayang.mvp.mvpframework.v.base.BaseActivity;
import com.jiayang.mvp.mvpframework.v.iview.MainActivityViewIpm;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainActivityPst> implements MainActivityViewIpm {

    private String[] strings = new String[]{"NumAnim" ,"TimeSelect" ,"ChangeBaseUrl"};
    private final Class<?>[] mClasses = {NumAnimActivity.class, TimeSelectActivity.class,
            ChangeBaseUrlActivity.class};
    private QuickAdapter<Model> mAdapter;
    private List<Model> mStringList = new ArrayList<>();

    //必须的权限 预防6.0动态权限   此处模拟 两个权限
    public String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE};
    @BindView(R.id.listView)
    ListView listView;

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkPermission();
        }

        LogUtils.e("JY");
        initAdapter();
        listView.setAdapter(mAdapter);
    }

    private void initAdapter() {
        for (int i = 0; i < strings.length; i++) {
            mStringList.add(new Model(strings[i], i));
        }
        mAdapter = new QuickAdapter<Model>(this, R.layout.item_adapter, mStringList) {


            @Override
            protected void convert(BaseAdapterHelper helper, final Model item) {
                helper.setText(R.id.item, item.mName);
                helper.setOnClickListener(R.id.item, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        goToActivity(mClasses[item.mInt]);

                    }
                });
            }
        };

    }

    private void goToActivity(Class activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);

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



}
