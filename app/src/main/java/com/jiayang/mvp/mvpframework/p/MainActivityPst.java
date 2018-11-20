package com.jiayang.mvp.mvpframework.p;

import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.m.service.LocationService;
import com.jiayang.mvp.mvpframework.p.base.BasePresenter;
import com.jiayang.mvp.mvpframework.v.iview.MainActivityViewIpm;

import javax.inject.Inject;

/**
 * Created by Administrator on 2017/8/31 0031.
 */

public class MainActivityPst extends BasePresenter<MainActivityViewIpm> {


    private LocationService locationService;



    @Inject
    public MainActivityPst(ErrorListener errorListener,LocationService locationService) {
        super(errorListener);
        this.locationService = locationService;
    }



}
