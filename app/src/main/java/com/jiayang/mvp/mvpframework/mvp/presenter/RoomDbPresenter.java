package com.jiayang.mvp.mvpframework.mvp.presenter;


import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.mvp.ipm.RoomDbViewIpm;

import javax.inject.Inject;


public class RoomDbPresenter extends BasePresenter<RoomDbViewIpm> {


    @Inject
    public RoomDbPresenter(ErrorListener errorListener) {
        super(errorListener);
    }
}