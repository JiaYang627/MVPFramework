package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.SplashViewIpm;
import com.jiayang.mvp.mvpframework.v.activity.MainActivity;

import javax.inject.Inject;


public class SplashPresenter extends BasePresenter<SplashViewIpm> {


    @Inject
    public SplashPresenter(ErrorListener errorListener) {
        super(errorListener);
    }

    public void goToMain() {
        mAppNavigate.startActivity(mContext, MainActivity.class, null, true);
    }
}