package com.jiayang.mvp.mvpframework.p;

import com.jiayang.mvp.mvpframework.m.rxhelper.ErrorListener;
import com.jiayang.mvp.mvpframework.p.base.BasePresenter;
import com.jiayang.mvp.mvpframework.v.iview.NumAnimActivityViewIpm;

import javax.inject.Inject;

/**
 * @author ：张 奎
 * @date ：2018-11-20 09：15
 * 邮箱   ：JiaYang627@163.com / zhang_k@hks360.com
 */
public class NumAnimActivityPst extends BasePresenter<NumAnimActivityViewIpm> {

    @Inject
    public NumAnimActivityPst(ErrorListener errorListener) {
        super(errorListener);
    }
}
