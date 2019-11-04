package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.VideoPlayerViewIpm;

import javax.inject.Inject;


public class VideoPlayerPresenter extends BasePresenter<VideoPlayerViewIpm> {


    @Inject
    public VideoPlayerPresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}