package com.jiayang.mvp.mvpframework.p;

import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.common.BasePresenter;
import com.jiayang.mvp.mvpframework.v.iview.TimeSelectActivityViewIpm;

import javax.inject.Inject;

/**
 * @author ：张 奎
 * @date ：2018-11-20 09：18
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class TimeSelectActivityPst extends BasePresenter<TimeSelectActivityViewIpm> {
    @Inject
    public TimeSelectActivityPst(ErrorListener errorListener) {
        super(errorListener);
    }
}
