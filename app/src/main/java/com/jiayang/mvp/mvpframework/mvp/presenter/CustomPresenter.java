package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.CustomViewIpm;

import javax.inject.Inject;


public class CustomPresenter extends BasePresenter<CustomViewIpm> {


    @Inject
    public CustomPresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}