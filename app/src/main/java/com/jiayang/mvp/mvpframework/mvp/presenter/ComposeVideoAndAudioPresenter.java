package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.ComposeVideoAndAudioViewIpm;

import javax.inject.Inject;


public class ComposeVideoAndAudioPresenter extends BasePresenter<ComposeVideoAndAudioViewIpm> {


    @Inject
    public ComposeVideoAndAudioPresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}