package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.SpannableViewIpm;

import javax.inject.Inject;


public class SpannablePresenter extends BasePresenter<SpannableViewIpm> {


    @Inject
    public SpannablePresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}