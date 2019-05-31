package com.jiayang.mvp.mvpframework.p;

import android.content.Intent;

import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.m.service.LocationService;
import com.jiayang.mvp.mvpframework.v.iview.TestActivityViewIpm;

import javax.inject.Inject;

/**
 * Created by 张 奎 on 2017-09-02 08:31.
 */

public class TestActivityPst extends BasePresenter<TestActivityViewIpm> {


    private LocationService locationService;

    @Inject
    public TestActivityPst(ErrorListener errorListener, LocationService locationService) {
        super(errorListener);
        this.locationService = locationService;
    }

    @Override
    public void getData(Intent intent) {
        String b = intent.getStringExtra("aaa");
//        Toast.makeText(context,  b, Toast.LENGTH_SHORT).show();

//        mView.showA(b);
//        Log.e("TestActivity aaa :", aaa + "");
    }
}
