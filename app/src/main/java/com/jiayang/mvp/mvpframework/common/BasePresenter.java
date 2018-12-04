package com.jiayang.mvp.mvpframework.common;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import javax.inject.Inject;


/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class BasePresenter<View extends BaseViewIpm> implements ErrorListener {
    @Inject
    protected AppNavigate mAppNavigate;

    protected View mView;
    protected Reference<View> reference;
    protected ErrorListener errorListener;
    protected Activity context;
    private boolean isViewAttach;

    public BasePresenter(ErrorListener errorListener) {
        this.errorListener = errorListener;
    }

    public void attachView(View view) {
        reference = new WeakReference<View>(view);
        mView = reference.get();
    }

    public void detachView(){
        if (reference != null) {
            reference.clear();
        }
        reference = null;
    }

    /**
     * 页面跳转 携带数据过来 Pst写此方法拿数据
     * @param intent
     */
    public void getData(Intent intent) {

    }

    /**
     * 页面跳转 数据回传 Pst写此方法拿回传数据
     * @param requestCode
     * @param requestCode1
     * @param data
     */
    public void onActivityResult(int requestCode, int requestCode1, Intent data) {

    }

    public void getContext(Activity context) {
        this.context = context;
    }

    /**
     * 如果需要进来就要联网请求数据，Pst 中覆写此方法
     */
    public void onTakeView() {
        if (!isViewAttach) {
            isViewAttach = true;
            onOnceTakeView();
        }
    }
    /**
     * 如果只需要第一次加载页面联网请求数据，Pst 中覆写次方法
     */
    public void onOnceTakeView() {

    }

    public void getArguments(Bundle bundle) {

    }
    public void onHiddenChanged(boolean hidden) {

    }

    @Override
    public void handleError(Throwable throwable) {
//        ErrorHelper.onError(context,throwable);
        handleNetError();
    }
    public void handleNetError() {

    }

    public void onNewIntent(Intent intent) {

    }
}
