package com.jiayang.mvp.mvpframework.p.base;

import android.content.Context;
import android.content.Intent;

import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.v.base.IBaseView;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;


/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class BasePresenter<View extends IBaseView>  {
    protected View mView;
    protected Reference<View> reference;
    protected ErrorListener errorListener;
    protected Context context;

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

    public void getContext(Context context) {
        this.context = context;
    }
}
