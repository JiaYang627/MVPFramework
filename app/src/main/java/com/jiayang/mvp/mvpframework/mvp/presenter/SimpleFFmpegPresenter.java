package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.SimpleFFmpegViewIpm;

import javax.inject.Inject;


public class SimpleFFmpegPresenter extends BasePresenter<SimpleFFmpegViewIpm> {


    @Inject
    public SimpleFFmpegPresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}