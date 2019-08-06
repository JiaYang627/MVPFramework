package com.jiayang.mvp.mvpframework.v.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;

import com.jiayang.commonlibs.commonListAdapter.BaseAdapterHelper;
import com.jiayang.commonlibs.commonListAdapter.QuickAdapter;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.bean.Model;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.Constants;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.CustomActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.SpannableActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.ZXingActivity;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.ToastUtilsBlankJ;
import com.jiayang.mvp.mvpframework.v.iview.MainActivityViewIpm;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainActivityPst> implements MainActivityViewIpm {

    private String[] strings = new String[]{"NumAnim", "TimeSelect",
            "ChangeBaseUrl", "ZXing", "Spannable","CustomView"};

    private final Class<?>[] mClasses = {NumAnimActivity.class, TimeSelectActivity.class,
            ChangeBaseUrlActivity.class, ZXingActivity.class, SpannableActivity.class, CustomActivity.class};


    private QuickAdapter<Model> mAdapter;
    private List<Model> mStringList = new ArrayList<>();

    //必须的权限 预防6.0动态权限   此处模拟 两个权限
    public String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE,Manifest.permission.CAMERA};
    @BindView(R.id.listView)
    ListView listView;
    private long mExitTime;
    private Disposable mPermissDis;

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
        goToActivity(activityClass, null, null);

    }

    private void goToActivity(Class activityClass, String key, String value) {
        Intent intent = new Intent(this, activityClass);
        if (!TextUtils.isEmpty(key)) {
            intent.putExtra(key, value);
        }
        startActivity(intent);

    }

    private void checkPermission() {

        mPermissDis = new RxPermissions(this)
                .request( Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean) {
                            ToastUtilsBlankJ.showShort("权限被拒绝");
                        } else {
                            // TODO
                        }
                    }
                });
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long cur = System.currentTimeMillis();
            if (cur - mExitTime > Constants.EXIT_INTERVAL_TIME) {
                ToastUtilsBlankJ.showShort(R.string.common_exit_two);
                mExitTime = cur;
                return true;
            } else {
                finish();
//                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPermissDis != null) {
            mPermissDis.dispose();
        }
    }
}
