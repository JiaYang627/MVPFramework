package com.jiayang.mvp.mvpframework.v.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.adapter.DividerGridItemDecoration;
import com.jiayang.mvp.mvpframework.adapter.MainActivityAdapter;
import com.jiayang.mvp.mvpframework.bean.Model;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.common.Constants;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.CustomActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.SimpleFFmpegActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.SpannableActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.VideoPlayerActivity;
import com.jiayang.mvp.mvpframework.mvp.ui.activity.ZXingActivity;
import com.jiayang.mvp.mvpframework.p.MainActivityPst;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.jiayang.mvp.mvpframework.utils.ToastUtilsBlankJ;
import com.jiayang.mvp.mvpframework.v.iview.MainActivityViewIpm;
import com.jiayang.testso.MyJNI;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity<MainActivityPst> implements MainActivityViewIpm {

    @BindView(R.id.textHookTextView)
    TextView textHookTextView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;




    private List<Model> mStringList = new ArrayList<>();

    //必须的权限 预防6.0动态权限   此处模拟 两个权限
    public String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA};
    private long mExitTime;
    private Disposable mPermissDis;
    private MainActivityAdapter mActivityAdapter;


    private String[] strings = new String[]{"NumAnim", "TimeSelect",
            "ChangeBaseUrl", "ZXing", "Spannable", "CustomView","VideoPlayer","SimpleFFmpeg"};

    private final Class<?>[] mClasses = {NumAnimActivity.class, TimeSelectActivity.class,
            ChangeBaseUrlActivity.class, ZXingActivity.class, SpannableActivity.class, CustomActivity.class, VideoPlayerActivity.class,
            SimpleFFmpegActivity.class};
    private final int RECORD_AUDIO = 100;

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

        LogUtils.e("调用so库的add方法" + MyJNI.add(10, 20));
    }


    private void initAdapter() {
        for (int i = 0; i < strings.length; i++) {
            mStringList.add(new Model(strings[i], i));
        }

        mActivityAdapter = new MainActivityAdapter(mStringList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mActivityAdapter);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        mActivityAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Model item = (Model) adapter.getData().get(position);
                goToActivity(mClasses[item.mInt]);
            }
        });
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
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE ,Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.MODIFY_AUDIO_SETTINGS)
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

    public void testHook(View view) {
        settext("没有被修改");
    }

    private void settext(String s) {
        textHookTextView.setText(s);
    }
}
