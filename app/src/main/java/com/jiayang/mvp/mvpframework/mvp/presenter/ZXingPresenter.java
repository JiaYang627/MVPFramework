package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.ZXingViewIpm;

import javax.inject.Inject;


public class ZXingPresenter extends BasePresenter<ZXingViewIpm> {


    @Inject
    public ZXingPresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}