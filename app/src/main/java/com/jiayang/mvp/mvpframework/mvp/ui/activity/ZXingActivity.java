package com.jiayang.mvp.mvpframework.mvp.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.FrameLayout;

import com.jiayang.mvp.mvpframework.R;
import com.jiayang.mvp.mvpframework.common.BaseActivity;
import com.jiayang.mvp.mvpframework.m.component.ApiComponent;
import com.jiayang.mvp.mvpframework.mvp.ipm.ZXingViewIpm;
import com.jiayang.mvp.mvpframework.mvp.presenter.ZXingPresenter;
import com.jiayang.mvp.mvpframework.utils.LogUtils;
import com.uuzuche.lib_zxing.activity.CaptureFragment;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;


public class ZXingActivity extends BaseActivity<ZXingPresenter> implements ZXingViewIpm {


    @BindView(R.id.second_button1)
    Button secondButton1;
    @BindView(R.id.fl_my_container)
    FrameLayout flMyContainer;
    @BindView(R.id.activity_second)
    FrameLayout activitySecond;
    private CodeUtils.AnalyzeCallback mAnalyzeCallback;
    private CaptureFragment mCaptureFragment;

    @Override
    protected void inject(ApiComponent apiComponent) {
        apiComponent.inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_zxing;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initCallBack();
        /**
         * 执行扫面Fragment的初始化操作
         */
        mCaptureFragment = new CaptureFragment();
        // 为二维码扫描界面设置定制化界面
        CodeUtils.setFragmentArgs(mCaptureFragment, R.layout.my_camera);

        mCaptureFragment.setAnalyzeCallback(mAnalyzeCallback);
        /**
         * 替换我们的扫描控件
         */ getSupportFragmentManager().beginTransaction().replace(R.id.fl_my_container, mCaptureFragment).commit();
    }

    private void initCallBack() {
        /**
         * 二维码解析回调函数
         */
        mAnalyzeCallback = new CodeUtils.AnalyzeCallback() {
            @Override
            public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                LogUtils.e("扫描成功,结果是 -- >"+ result);
                Message obtain = Message.obtain();
                obtain.what=R.id.restart_preview;
                mCaptureFragment.getHandler().sendMessageDelayed(obtain, 500);

            }

            @Override
            public void onAnalyzeFailed() {
                LogUtils.e("扫描失败");
            }
        };
    }
}
